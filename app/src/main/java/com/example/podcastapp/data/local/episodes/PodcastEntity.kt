package com.example.podcastapp.data.local.episodes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PodcastEntity(
    val description: String = "",
    val thumbnail: String = "",
    val title: String = "",
    val image: String ="",
    val language: String = "",
    val explicitContent: Boolean = false,
    val nextEpisodePubDate: Long = 0,
    val totalEpisodes: Int = 0,
    val audioLengthSec: Int = 0,
    @PrimaryKey
    val id: String = "",
)