package com.example.podcastapp.data.service

import android.content.ComponentName
import android.content.Context
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.podcastapp.domain.model.podcast.Episode
import com.example.podcastapp.domain.model.podcast.Podcast
import com.example.podcastapp.exoplayer.PodcastMediaSource
import com.example.podcastapp.util.consts.Constants.START_MEDIA_ACTION

class PodcastServiceConnection(
    context: Context,
    private val podcastMediaSource: PodcastMediaSource
) {

    var playbackState by mutableStateOf<PlaybackStateCompat?>(null)
    var currentPlayingEpisode by mutableStateOf<Episode?>(null)

    private var isConnected = false

    lateinit var mediaController: MediaControllerCompat

    val transportControls: MediaControllerCompat.TransportControls
        get() = mediaController.transportControls

    private val mediaBrowserConnectionCallback = MediaBrowserConnectionCallback(context)

    private val mediaBrowser = MediaBrowserCompat(
        context,
        ComponentName(context, PodcastService::class.java),
        mediaBrowserConnectionCallback,
        null
    ).apply {
        connect()
    }

    fun playPodcast(podcast: Podcast) {
        podcastMediaSource.setPodcast(podcast)
        mediaBrowser.sendCustomAction(START_MEDIA_ACTION, null, null)
    }

    fun subscribe(parentId: String, callback: MediaBrowserCompat.SubscriptionCallback) {
        mediaBrowser.subscribe(parentId, callback)
    }

    fun unsubscribe(parentId: String, callback: MediaBrowserCompat.SubscriptionCallback) {
        mediaBrowser.unsubscribe(parentId, callback)
    }

    private inner class MediaBrowserConnectionCallback(
        private val context: Context
    ) : MediaBrowserCompat.ConnectionCallback() {

        override fun onConnected() {
            super.onConnected()
            isConnected = true
            mediaController = MediaControllerCompat(context, mediaBrowser.sessionToken).apply {
                registerCallback(MediaControllerCallback())
            }
        }

        override fun onConnectionSuspended() {
            super.onConnectionSuspended()
            isConnected = false
        }

        override fun onConnectionFailed() {
            super.onConnectionFailed()
            isConnected = false
        }
    }

    private inner class MediaControllerCallback : MediaControllerCompat.Callback() {

        override fun onPlaybackStateChanged(state: PlaybackStateCompat?) {
            playbackState = state
        }

        override fun onMetadataChanged(metadata: MediaMetadataCompat?) {
            currentPlayingEpisode = metadata?.let {
                podcastMediaSource.podcastEpisodes.find {
                    it.id == metadata.description?.mediaId
                }
            }
        }

        override fun onSessionDestroyed() {
            super.onSessionDestroyed()
            mediaBrowserConnectionCallback.onConnectionSuspended()
        }
    }
}