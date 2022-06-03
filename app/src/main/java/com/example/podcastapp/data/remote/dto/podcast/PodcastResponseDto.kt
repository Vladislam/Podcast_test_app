package com.example.podcastapp.data.remote.dto.podcast


import com.google.gson.annotations.SerializedName

data class PodcastResponseDto(
    val id: String,
    val description: String,
    val thumbnail: String,
    val title: String,
    val episodes: List<EpisodeDto>,
    val image: String,
    val language: String,
    @SerializedName("explicit_content")
    val explicitContent: Boolean,
    @SerializedName("next_episode_pub_date")
    val nextEpisodePubDate: Long,
    @SerializedName("total_episodes")
    val totalEpisodes: Int,
    @SerializedName("audio_length_sec")
    val audioLengthSec: Int,
)