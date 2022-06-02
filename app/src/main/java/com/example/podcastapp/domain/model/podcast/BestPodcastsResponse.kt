package com.example.podcastapp.domain.model.podcast

data class BestPodcastsResponse(
    val podcasts: List<BestPodcast>,
    val id: Int = 0,
    val name: String = "",
    val total: Int = 0,
    val hasNext: Boolean = false,
    val hasPrevious: Boolean = false,
    val listenNotesUrl: String = "",
    val nextPageNumber: Int = 1,
    val pageNumber: Int? = null,
    val previousPageNumber: Int = 0,
)
