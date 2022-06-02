package com.example.podcastapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.podcastapp.data.local.best_podcast.BestPodcastDao
import com.example.podcastapp.data.local.best_podcast.BestPodcastEntity

@Database(
    entities = [BestPodcastEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PodcastDatabase : RoomDatabase() {

    abstract val bestPodcastDao: BestPodcastDao

    companion object {
        const val DB_NAME = "podcast.db"
    }
}