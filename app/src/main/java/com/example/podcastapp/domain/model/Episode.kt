package com.example.podcastapp.domain.model


data class Episode(
    val audio: String,
    val audioLengthSec: Int,
    val descriptionHighlighted: String,
    val descriptionOriginal: String,
    val explicitContent: Boolean,
    val guidFromRss: String,
    val id: String,
    val image: String,
    val itunesId: Int,
    val link: String,
    val listenNotesUrl: String,
    val podcast: Podcast,
    val pubDateMs: Long,
    val rss: String,
    val thumbnail: String,
    val titleHighlighted: String,
    val titleOriginal: String,
    val transcriptsHighlighted: List<Any>
)