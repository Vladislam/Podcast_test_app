package com.example.podcastapp.domain.model


data class Podcast(
    val genreIds: List<Int>,
    val id: String,
    val image: String,
    val listenScore: Int,
    val listenScoreGlobalRank: String,
    val listenNotesUrl: String,
    val publisherHighlighted: String,
    val publisherOriginal: String,
    val thumbnail: String,
    val titleHighlighted: String,
    val titleOriginal: String
)