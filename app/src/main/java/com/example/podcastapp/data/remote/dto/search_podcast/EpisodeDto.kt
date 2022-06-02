package com.example.podcastapp.data.remote.dto.search_podcast

import com.google.gson.annotations.SerializedName

data class EpisodeDto(
    val audio: String,
    val id: String,
    val image: String,
    val link: String,
    val podcast: PodcastDto,
    val thumbnail: String,
    @SerializedName("listennotes_url")
    val listenNotesUrl: String,
    @SerializedName("pub_date_ms")
    val pubDateMs: Long,
    @SerializedName("title_original")
    val titleOriginal: String,
    @SerializedName("audio_length_sec")
    val audioLengthSec: Int,
    @SerializedName("description_original")
    val descriptionOriginal: String,
    @SerializedName("explicit_content")
    val explicitContent: Boolean,
)