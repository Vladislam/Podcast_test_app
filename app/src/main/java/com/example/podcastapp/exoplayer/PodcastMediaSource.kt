package com.example.podcastapp.exoplayer

import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.MediaMetadataCompat
import androidx.core.net.toUri
import com.example.podcastapp.domain.model.podcast.Episode
import com.example.podcastapp.domain.model.podcast.Podcast
import com.example.podcastapp.exoplayer.PodcastSourceState.*
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PodcastMediaSource @Inject constructor() {
    var mediaMetadataEpisodes: List<MediaMetadataCompat> = emptyList()
    var podcastEpisodes: List<Episode> = emptyList()
        private set

    private val onReadyListeners = mutableListOf<OnReadyListener>()

    private var state = CREATED
        set(value) {
            if (value == INITIALIZED || value == ERROR) {
                synchronized(onReadyListeners) {
                    field = value
                    onReadyListeners.forEach {
                        it.invoke(state == INITIALIZED)
                    }
                }
            } else field = value
        }

    fun setPodcast(podcast: Podcast) {
        state = INITIALIZING
        podcastEpisodes = podcast.episodes
        mediaMetadataEpisodes = podcast.episodes.map { episode ->
            MediaMetadataCompat.Builder()
                .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, episode.id)
                .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, podcast.publisher)
                .putString(MediaMetadataCompat.METADATA_KEY_TITLE, episode.title)
                .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_SUBTITLE, episode.title)
                .putString(
                    MediaMetadataCompat.METADATA_KEY_DISPLAY_DESCRIPTION,
                    episode.description
                )
                .putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI, episode.image)
                .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI, episode.image)
                .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI, episode.audio)
                .build()
        }
        state = INITIALIZED
    }

    fun asMediaSource(dataSourceFactory: DataSource.Factory): ConcatenatingMediaSource {
        val concatenatingMediaSource = ConcatenatingMediaSource()
        mediaMetadataEpisodes.forEach { episode ->
            val mediaItem = MediaItem.fromUri(
                episode.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI).toUri()
            )
            val mediaSource =
                ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(mediaItem)
            concatenatingMediaSource.addMediaSource(mediaSource)
        }
        return concatenatingMediaSource
    }

    fun asMediaItems() = mediaMetadataEpisodes.map { metadata ->
        val description = MediaDescriptionCompat.Builder()
            .setMediaId(metadata.description.mediaId)
            .setTitle(metadata.description.title)
            .setSubtitle(metadata.description.subtitle)
            .setIconUri(metadata.description.iconUri)
            .setMediaUri(metadata.description.mediaUri)
            .setDescription(metadata.description.description)
            .build()
        MediaBrowserCompat.MediaItem(description, MediaBrowserCompat.MediaItem.FLAG_PLAYABLE)
    }.toMutableList()

    fun whenReady(action: (Boolean) -> Unit): Boolean {
        return if (state == CREATED || state == INITIALIZING) {
            onReadyListeners += action
            false
        } else {
            action(state == INITIALIZED)
            true
        }
    }

    fun refresh() {
        onReadyListeners.clear()
        state = CREATED
    }
}

typealias OnReadyListener = (Boolean) -> Unit

enum class PodcastSourceState {
    CREATED,
    INITIALIZING,
    INITIALIZED,
    ERROR
}