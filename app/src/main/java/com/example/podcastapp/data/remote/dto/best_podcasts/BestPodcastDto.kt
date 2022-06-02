package com.example.podcastapp.data.remote.dto.best_podcasts


import com.google.gson.annotations.SerializedName

data class BestPodcastDto(
    val country: String,
    val description: String,
    val website: String,
    val id: String,
    val image: String,
    val language: String,
    val publisher: String,
    val thumbnail: String,
    val title: String,
    @SerializedName("total_episodes")
    val totalEpisodes: Int,
    @SerializedName("earliest_pub_date_ms")
    val earliestPubDateMs: Long,
    @SerializedName("explicit_content")
    val explicitContent: Boolean,
    @SerializedName("audio_length_sec")
    val audioLengthSec: Int,
    @SerializedName("listennotes_url")
    val listenNotesUrl: String,
    @SerializedName("listen_score")
    val listenScore: Int,
)