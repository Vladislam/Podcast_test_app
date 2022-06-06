package com.example.podcastapp.data.repository

import com.example.podcastapp.R
import com.example.podcastapp.data.local.PodcastDatabase
import com.example.podcastapp.data.mapper.*
import com.example.podcastapp.data.remote.PodcastApi
import com.example.podcastapp.domain.model.UiText
import com.example.podcastapp.domain.model.best_podcast.BestPodcastsResponse
import com.example.podcastapp.domain.model.podcast.Podcast
import com.example.podcastapp.domain.model.search_podcast.SearchPodcastsResponse
import com.example.podcastapp.domain.repository.PodcastRepository
import com.example.podcastapp.util.Resource
import com.example.podcastapp.util.consts.Constants.SEARCH_TYPE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PodcastRepositoryImpl @Inject constructor(
    private val api: PodcastApi,
    private val podcastDb: PodcastDatabase,
) : PodcastRepository {

    override suspend fun searchPodcasts(
        query: String,
        offset: Int
    ): Resource<SearchPodcastsResponse> {
        return try {
            val response = api.searchPodcasts(query, offset, SEARCH_TYPE)
            Resource.Success(response.toPodcastSearch())
        } catch (e: Exception) {
            Resource.Error(UiText.StringResource(R.string.couldnt_load_podcasts))
        }
    }

    override suspend fun getBestPodcasts(
        fetchFromRemote: Boolean,
        page: Int,
        language: String
    ): Flow<Resource<BestPodcastsResponse>> {
        return flow {
            emit(Resource.Loading(true))
            val localBestPodcasts = podcastDb.bestPodcastDao.getBestPodcasts()
            emit(Resource.Success(BestPodcastsResponse(podcasts = localBestPodcasts.map { it.toBestPodcast() })))

            val isDbEmpty = localBestPodcasts.isEmpty()
            val loadFromCache = !isDbEmpty && !fetchFromRemote
            if (loadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteBestPodcasts = try {
                api.getBestPodcasts(page, language)
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.couldnt_load_podcasts)))
                null
            }

            remoteBestPodcasts?.let { podcasts ->
                if (page == 1)
                    podcastDb.bestPodcastDao.deleteAll()
                podcastDb.bestPodcastDao.insert(podcasts.podcasts.map { it.toBestPodcastEntity() })
                emit(Resource.Success(podcasts.toBestPodcasts()))
            }
            emit(Resource.Loading(false))
        }
    }

    override suspend fun getPodcastWithEpisodes(
        fetchFromRemote: Boolean,
        podcastId: String,
        nextEpisodePubDate: Long?,
    ): Flow<Resource<Podcast>> {
        return flow {
            emit(Resource.Loading(true))
            val localPodcastWithEpisodes = podcastDb.podcastDao.getPodcastWithEpisodes(podcastId)
            val isDbEmpty =
                localPodcastWithEpisodes == null || localPodcastWithEpisodes.episodes.isEmpty()

            if (!isDbEmpty)
                localPodcastWithEpisodes?.let { emit(Resource.Success(it.toPodcast())) }

            val loadFromCache = !isDbEmpty && !fetchFromRemote
            if (loadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remotePodcast = try {
                api.getPodcastWitEpisodes(podcastId, nextEpisodePubDate)
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(UiText.StringResource(R.string.couldnt_load_podcasts)))
                null
            }

            remotePodcast?.let { remote ->
                podcastDb.podcastDao.insertEpisodes(remote.episodes.map { it.toEpisodeEntity(remote.id) })
                if (localPodcastWithEpisodes != null && nextEpisodePubDate == localPodcastWithEpisodes.podcast.nextEpisodePubDate) {
                    emit(
                        Resource.Success(
                            remote.toPodcast().also { podcast ->
                                podcast.episodes.toMutableList()
                                    .addAll(localPodcastWithEpisodes.episodes.map { it.toEpisode() })
                            })
                    )
                } else {
                    podcastDb.podcastDao.insertPodcast(remote.toPodcastEntity())
                    emit(Resource.Success(remote.toPodcast()))
                }
            }
            emit(Resource.Loading(false))
        }
    }
}