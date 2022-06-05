package com.example.podcastapp.domain.model.search_podcast


data class SearchPodcastsResponse(
    val count: Int,
    val total: Int,
    val nextOffset: Int,
    val podcasts: List<SearchPodcast>,
)