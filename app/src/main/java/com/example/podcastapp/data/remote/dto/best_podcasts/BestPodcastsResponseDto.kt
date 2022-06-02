package com.example.podcastapp.data.remote.dto.best_podcasts


import com.google.gson.annotations.SerializedName

data class BestPodcastsResponseDto(
    val id: Int,
    val name: String,
    val total: Int,
    @SerializedName("has_next")
    val hasNext: Boolean,
    @SerializedName("has_previous")
    val hasPrevious: Boolean,
    @SerializedName("listennotes_url")
    val listenNotesUrl: String,
    @SerializedName("next_page_number")
    val nextPageNumber: Int,
    @SerializedName("page_number")
    val pageNumber: Int,
    @SerializedName("podcasts")
    val podcasts: List<BestPodcastDto>,
    @SerializedName("previous_page_number")
    val previousPageNumber: Int,
)