package com.example.podcastapp.presentation.podcast_screen

import com.example.podcastapp.domain.model.UiText
import com.example.podcastapp.domain.model.best_podcast.BestPodcast
import com.example.podcastapp.domain.model.search_podcast.SearchPodcastsResponse

data class PodcastState(
    val bestPodcasts: List<BestPodcast> = emptyList(),
    val searchPodcast: SearchPodcastsResponse? = null,
    val isLoading: Boolean = false,
    val error: UiText? = null,
)