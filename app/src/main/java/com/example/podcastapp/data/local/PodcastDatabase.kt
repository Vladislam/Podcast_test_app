package com.example.podcastapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.podcastapp.data.local.best_podcast.BestPodcastDao
import com.example.podcastapp.data.local.best_podcast.BestPodcastEntity
import com.example.podcastapp.data.local.episodes.EpisodeEntity
import com.example.podcastapp.data.local.episodes.PodcastDao
import com.example.podcastapp.data.local.episodes.PodcastEntity

@Database(
    entities = [PodcastEntity::class, EpisodeEntity::class, BestPodcastEntity::class],
    version = 5,
    exportSchema = false
)
abstract class PodcastDatabase : RoomDatabase() {

    abstract val bestPodcastDao: BestPodcastDao
    abstract val podcastDao: PodcastDao

    companion object {
        const val DB_NAME = "podcast.db"
    }
}