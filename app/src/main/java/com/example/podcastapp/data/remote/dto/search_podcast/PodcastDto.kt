package com.example.podcastapp.data.remote.dto.search_podcast


import com.google.gson.annotations.SerializedName

data class PodcastDto(
    val id: String,
    val image: String,
    val thumbnail: String,
    @SerializedName("listennotes_url")
    val listenNotesUrl: String,
    @SerializedName("publisher_highlighted")
    val publisherHighlighted: String,
    @SerializedName("publisher_original")
    val publisherOriginal: String,
    @SerializedName("title_original")
    val titleOriginal: String,
)