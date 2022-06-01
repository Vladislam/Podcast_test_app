package com.example.podcastapp.data.remote.dto


import com.google.gson.annotations.SerializedName

data class PodcastSearchDto(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next_offset")
    val nextOffset: Int,
    @SerializedName("results")
    val episodes: List<EpisodeDto>,
    @SerializedName("took")
    val took: Double,
    @SerializedName("total")
    val total: Int
)