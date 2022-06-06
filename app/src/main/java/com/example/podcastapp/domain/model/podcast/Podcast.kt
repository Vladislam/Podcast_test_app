package com.example.podcastapp.domain.model.podcast


data class Podcast(
    val id: String = "",
    val description: String = "",
    val thumbnail: String = "",
    val title: String = "",
    val episodes: List<Episode> = emptyList(),
    val image: String = "",
    val language: String = "",
    val publisher: String = "",
    val explicitContent: Boolean = false,
    val nextEpisodePubDate: Long = 0L,
    val totalEpisodes: Int = 0,
    val audioLengthSec: Int = 0,
)