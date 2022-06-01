package com.example.podcastapp.domain.model


data class PodcastSearch(
    val count: Int,
    val nextOffset: Int,
    val episodes: List<Episode>,
    val took: Double,
    val total: Int
)