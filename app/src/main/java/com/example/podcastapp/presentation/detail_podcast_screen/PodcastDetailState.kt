package com.example.podcastapp.presentation.detail_podcast_screen

import com.example.podcastapp.domain.model.UiText
import com.example.podcastapp.domain.model.podcast.Podcast

data class PodcastDetailState(
    val podcast: Podcast = Podcast(),
    val isLoading: Boolean = false,
    val error: UiText? = null,
)
