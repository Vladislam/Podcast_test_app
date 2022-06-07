package com.example.podcastapp.data.local.episodes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PodcastEntity(
    val description: String = "",
    val thumbnail: String = "",
    val title: String = "",
    val image: String = "",
    val language: String = "",
    val explicitContent: Boolean = false,
    val nextEpisodePubDate: Long = 0L,
    val totalEpisodes: Int = 0,
    val audioLengthSec: Int = 0,
    val publisher: String = "",
    val website: String = "",
    val listenNotesUrl: String = "",
    val firstPubDateMs: Long = 0L,
    @PrimaryKey
    val id: String = "",
)