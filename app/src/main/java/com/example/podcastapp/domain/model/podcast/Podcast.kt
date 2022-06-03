package com.example.podcastapp.domain.model.podcast


data class Podcast(
    val id: String,
    val description: String,
    val thumbnail: String,
    val title: String,
    val episodes: List<Episode>,
    val image: String,
    val language: String,
    val explicitContent: Boolean,
    val nextEpisodePubDate: Long,
    val totalEpisodes: Int,
    val audioLengthSec: Int,
)