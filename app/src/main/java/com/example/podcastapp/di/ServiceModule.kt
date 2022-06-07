package com.example.podcastapp.di

import android.content.Context
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.database.StandaloneDatabaseProvider
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.upstream.cache.CacheDataSource
import com.google.android.exoplayer2.upstream.cache.NoOpCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import com.google.android.exoplayer2.util.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped
import java.io.File

@Module
@InstallIn(ServiceComponent::class)
object ServiceModule {

    @Provides
    @ServiceScoped
    fun provideAudioAttributes(): AudioAttributes =
        AudioAttributes.Builder()
            .setContentType(C.CONTENT_TYPE_MUSIC)
            .setUsage(C.USAGE_MEDIA)
            .build()

    @Provides
    @ServiceScoped
    fun provideExoPlayer(
        @ApplicationContext context: Context,
        audioAttributes: AudioAttributes
    ): ExoPlayer = ExoPlayer.Builder(context)
        .build()
        .apply {
            setAudioAttributes(audioAttributes, true)
            setHandleAudioBecomingNoisy(true)

        }

    @Provides
    @ServiceScoped
    fun provideDataSourceFactory(
        @ApplicationContext context: Context
    ) = DefaultDataSource.Factory(
        context
    ) {
        DefaultDataSource(
            context,
            Util.getUserAgent(context, context.packageName),
            false
        )
    }

    @Provides
    @ServiceScoped
    fun provideCacheDataSourceFactory(
        @ApplicationContext context: Context,
        datasourceFactory: DefaultDataSource.Factory
    ): CacheDataSource.Factory {
        val cacheDir = File(context.cacheDir, "media")
        val databaseProvider = StandaloneDatabaseProvider(context)
        val cache = SimpleCache(cacheDir, NoOpCacheEvictor(), databaseProvider)
        return CacheDataSource.Factory().apply {
            setCache(cache)
            setUpstreamDataSourceFactory(datasourceFactory)
        }
    }
}