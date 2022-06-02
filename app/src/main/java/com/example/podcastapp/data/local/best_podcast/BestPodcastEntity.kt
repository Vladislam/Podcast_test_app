package com.example.podcastapp.data.local.best_podcast

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BestPodcastEntity(
    val country: String = "",
    val description: String = "",
    val website: String? = "",
    val image: String = "",
    val language: String = "",
    val publisher: String = "",
    val thumbnail: String = "",
    val title: String = "",
    val totalEpisodes: Int = 0,
    val earliestPubDateMs: Long = 0,
    val explicitContent: Boolean = false,
    val audioLengthSec: Int = 0,
    val listenNotesUrl: String = "",
    @PrimaryKey
    val id: String = "",
)
