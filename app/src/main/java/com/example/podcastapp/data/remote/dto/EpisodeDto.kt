package com.example.podcastapp.data.remote.dto


import com.google.gson.annotations.SerializedName

data class EpisodeDto(
    @SerializedName("audio")
    val audio: String,
    @SerializedName("audio_length_sec")
    val audioLengthSec: Int,
    @SerializedName("description_highlighted")
    val descriptionHighlighted: String,
    @SerializedName("description_original")
    val descriptionOriginal: String,
    @SerializedName("explicit_content")
    val explicitContent: Boolean,
    @SerializedName("guid_from_rss")
    val guidFromRss: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("itunes_id")
    val itunesId: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("listennotes_url")
    val listenNotesUrl: String,
    @SerializedName("podcast")
    val podcast: PodcastDto,
    @SerializedName("pub_date_ms")
    val pubDateMs: Long,
    @SerializedName("rss")
    val rss: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("title_highlighted")
    val titleHighlighted: String,
    @SerializedName("title_original")
    val titleOriginal: String,
    @SerializedName("transcripts_highlighted")
    val transcriptsHighlighted: List<Any>
)