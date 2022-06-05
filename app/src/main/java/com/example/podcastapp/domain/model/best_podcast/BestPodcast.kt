package com.example.podcastapp.domain.model.best_podcast


data class BestPodcast(
    val country: String,
    val description: String,
    val website: String?,
    val id: String,
    val image: String,
    val language: String,
    val publisher: String,
    val thumbnail: String,
    val title: String,
    val totalEpisodes: Int,
    val earliestPubDateMs: Long,
    val explicitContent: Boolean,
    val audioLengthSec: Int,
    val listenNotesUrl: String,
)
