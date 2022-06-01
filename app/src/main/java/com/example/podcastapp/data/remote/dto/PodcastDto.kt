package com.example.podcastapp.data.remote.dto


import com.google.gson.annotations.SerializedName

data class PodcastDto(
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("listen_score")
    val listenScore: Int,
    @SerializedName("listen_score_global_rank")
    val listenScoreGlobalRank: String,
    @SerializedName("listennotes_url")
    val listenNotesUrl: String,
    @SerializedName("publisher_highlighted")
    val publisherHighlighted: String,
    @SerializedName("publisher_original")
    val publisherOriginal: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("title_highlighted")
    val titleHighlighted: String,
    @SerializedName("title_original")
    val titleOriginal: String
)