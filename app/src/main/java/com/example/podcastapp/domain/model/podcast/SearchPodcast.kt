package com.example.podcastapp.domain.model.podcast


data class SearchPodcast(
    val id: String,
    val image: String,
    val thumbnail: String,
    val listenNotesUrl: String,
    val publisherHighlighted: String,
    val publisherOriginal: String,
    val titleOriginal: String,
)