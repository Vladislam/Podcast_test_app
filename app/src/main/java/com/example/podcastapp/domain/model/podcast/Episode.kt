package com.example.podcastapp.domain.model.podcast


data class Episode(
    val audio: String,
    val id: String,
    val image: String,
    val thumbnail: String,
    val title: String,
    val maybeAudioInvalid: Boolean,
    val pubDateMs: Long,
    val audioLengthSec: Int,
    val description: String,
    val explicitContent: Boolean,
)