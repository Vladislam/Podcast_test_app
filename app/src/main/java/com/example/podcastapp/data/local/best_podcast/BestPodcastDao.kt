package com.example.podcastapp.data.local.best_podcast

import androidx.room.*

@Dao
interface BestPodcastDao {

    @Query("SELECT * FROM bestpodcastentity")
    suspend fun getBestPodcasts(): List<BestPodcastEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(podcasts: List<BestPodcastEntity>)

    @Insert
    suspend fun insert(podcast: BestPodcastEntity)

    @Delete
    suspend fun delete(podcast: BestPodcastEntity)

    @Query("DELETE FROM BestPodcastEntity")
    suspend fun deleteAll()
}