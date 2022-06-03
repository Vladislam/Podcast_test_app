package com.example.podcastapp.data.remote.dto.podcast


import com.google.gson.annotations.SerializedName

data class EpisodeDto(
    val audio: String,
    val id: String,
    val image: String,
    val thumbnail: String,
    val title: String,
    @SerializedName("maybe_audio_invalid")
    val maybeAudioInvalid: Boolean,
    @SerializedName("pub_date_ms")
    val pubDateMs: Long,
    @SerializedName("audio_length_sec")
    val audioLengthSec: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("explicit_content")
    val explicitContent: Boolean,
)