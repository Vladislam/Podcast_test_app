package com.example.podcastapp.data.remote

import com.example.podcastapp.data.remote.dto.best_podcasts.BestPodcastsResponseDto
import com.example.podcastapp.data.remote.dto.search_podcast.PodcastSearchDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PodcastApi {

    companion object {
        private const val SEARCH = "search"
        private const val BEST_PODCASTS = "best_podcasts"
        const val BASE_URL = "https://listen-api.listennotes.com/api/v2/"
    }

    @GET(SEARCH)
    suspend fun searchPodcasts(
        @Query("q") query: String,
        @Query("offset") offset: Int,
    ): PodcastSearchDto

    @GET(BEST_PODCASTS)
    suspend fun getBestPodcasts(
        @Query("page") page: Int,
        @Query("language") language: String,
        //TODO: ADD GENRE IDs
    ): BestPodcastsResponseDto
}