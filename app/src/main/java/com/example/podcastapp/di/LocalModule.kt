package com.example.podcastapp.di

import android.content.Context
import androidx.room.Room
import com.example.podcastapp.data.local.PodcastDatabase
import com.example.podcastapp.data.local.PodcastDatabase.Companion.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun providePodcastDatabase(@ApplicationContext context: Context): PodcastDatabase =
        Room.databaseBuilder(context, PodcastDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
}