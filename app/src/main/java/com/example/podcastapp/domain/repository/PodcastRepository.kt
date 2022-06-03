package com.example.podcastapp.domain.repository

import com.example.podcastapp.domain.model.podcast.BestPodcastsResponse
import com.example.podcastapp.domain.model.podcast.SearchPodcastsResponse
import com.example.podcastapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface PodcastRepository {

    suspend fun searchPodcasts(
        query: String,
        offset: Int,
    ): Resource<SearchPodcastsResponse>

    suspend fun getBestPodcasts(
        fetchFromRemote: Boolean,
        page: Int,
        language: String,
    ): Flow<Resource<BestPodcastsResponse>>
}