package com.example.podcastapp.data.repository

import com.example.podcastapp.R
import com.example.podcastapp.data.mapper.toPodcastSearch
import com.example.podcastapp.data.remote.PodcastApi
import com.example.podcastapp.domain.model.PodcastSearch
import com.example.podcastapp.domain.model.UiText
import com.example.podcastapp.domain.repository.PodcastRepository
import com.example.podcastapp.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PodcastRepositoryImpl @Inject constructor(
    private val api: PodcastApi,
) : PodcastRepository {

    override suspend fun searchPodcasts(
        query: String,
        offset: Int
    ): Resource<PodcastSearch> {
        return try {
            val response = api.searchPodcasts(query, offset)
            Resource.Success(response.toPodcastSearch())
        } catch (e: Exception) {
            Resource.Error(UiText.StringResource(R.string.couldnt_load_podcasts))
        }
    }
}