package com.example.podcastapp.data.local.episodes


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EpisodeEntity(
    val podcastId: String = "",
    val audio: String = "",
    val image: String = "",
    val thumbnail: String = "",
    val title: String = "",
    val maybeAudioInvalid: Boolean = false,
    val pubDateMs: Long = 0,
    val audioLengthSec: Int = 0,
    val description: String = "",
    val explicitContent: Boolean = false,
    @PrimaryKey
    val id: String = "",
)