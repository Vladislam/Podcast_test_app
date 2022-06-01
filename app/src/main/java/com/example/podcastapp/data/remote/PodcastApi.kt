package com.example.podcastapp.data.remote

import com.example.podcastapp.data.remote.dto.PodcastSearchDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PodcastApi {

    companion object {
        private const val SEARCH = "search"
        const val BASE_URL = "https://listen-api.listennotes.com/api/v2/"
    }

    @GET(SEARCH)
    suspend fun searchPodcasts(
        @Query("q") query: String,
        @Query("offset") offset: Int,
    ): PodcastSearchDto
}