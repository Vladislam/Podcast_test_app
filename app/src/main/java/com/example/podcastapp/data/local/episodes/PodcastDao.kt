package com.example.podcastapp.data.local.episodes

import androidx.room.*

@Dao
interface PodcastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPodcast(podcast: PodcastEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisode(episode: EpisodeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisodes(episode: List<EpisodeEntity>)

    @Update
    suspend fun updatePodcast(podcast: PodcastEntity)

    @Update
    suspend fun updateEpisode(episode: EpisodeEntity)

    @Query("SELECT * FROM PodcastEntity WHERE id = :podcastId")
    fun getPodcastWithEpisodes(podcastId: String): PodcastWithEpisodes?

    @Query("SELECT * FROM PodcastEntity")
    suspend fun getPodcasts(): List<PodcastEntity>

    @Query("DELETE FROM PodcastEntity WHERE id = :id")
    suspend fun deletePodcast(id: String)

    @Query("DELETE FROM EpisodeEntity WHERE id = :id")
    suspend fun deleteEpisode(id: String)
}