package com.example.podcastapp.domain.repository

import com.example.podcastapp.domain.model.PodcastSearch
import com.example.podcastapp.util.Resource

interface PodcastRepository {

    suspend fun searchPodcasts(
        query: String,
        offset: Int,
    ): Resource<PodcastSearch>
}