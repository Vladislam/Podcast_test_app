package com.example.podcastapp.domain.model.podcast


data class PodcastSearch(
    val count: Int,
    val total: Int,
    val nextOffset: Int,
    val episodes: List<Episode>,
)