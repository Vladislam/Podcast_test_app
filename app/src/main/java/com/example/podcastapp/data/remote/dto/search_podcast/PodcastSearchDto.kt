package com.example.podcastapp.data.remote.dto.search_podcast


import com.google.gson.annotations.SerializedName

data class PodcastSearchDto(
    val count: Int,
    val total: Int,
    @SerializedName("next_offset")
    val nextOffset: Int,
    @SerializedName("results")
    val episodes: List<EpisodeDto>,
)