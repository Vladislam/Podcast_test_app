package com.example.podcastapp.data.local.episodes

import androidx.room.Embedded
import androidx.room.Relation

data class PodcastWithEpisodes(
    @Embedded val podcast: PodcastEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "podcastId"
    )
    val episodes: List<EpisodeEntity>
)