package com.example.podcastapp.data.remote

import com.example.podcastapp.data.remote.dto.best_podcasts.BestPodcastsResponseDto
import com.example.podcastapp.data.remote.dto.podcast.PodcastResponseDto
import com.example.podcastapp.data.remote.dto.search_podcast.SearchPodcastsResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PodcastApi {

    companion object {
        private const val SEARCH = "search"
        private const val BEST_PODCASTS = "best_podcasts"
        private const val PODCASTS = "podcasts/{id}"
        const val BASE_URL = "https://listen-api.listennotes.com/api/v2/"
    }

    @GET(SEARCH)
    suspend fun searchPodcasts(
        @Query("q") query: String,
        @Query("offset") offset: Int,
        @Query("type") type: String
    ): SearchPodcastsResponseDto

    @GET(BEST_PODCASTS)
    suspend fun getBestPodcasts(
        @Query("page") page: Int,
        @Query("language") language: String,
        //TODO: ADD GENRE IDs
    ): BestPodcastsResponseDto

    @GET(PODCASTS)
    suspend fun getPodcastWitEpisodes(
        @Path("id") id: String,
        @Query("next_episode_pub_date") nextEpisodePubDate: Long?,
    ): PodcastResponseDto
}