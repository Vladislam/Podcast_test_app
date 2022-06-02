package com.example.podcastapp.domain.model.podcast


data class Episode(
    val audio: String,
    val id: String,
    val image: String,
    val link: String,
    val podcast: Podcast,
    val thumbnail: String,
    val listenNotesUrl: String,
    val pubDateMs: Long,
    val titleOriginal: String,
    val audioLengthSec: Int,
    val descriptionOriginal: String,
    val explicitContent: Boolean,
)