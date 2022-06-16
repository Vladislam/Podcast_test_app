package com.example.podcastapp.data.service

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.media.MediaBrowserServiceCompat
import com.example.podcastapp.exoplayer.PodcastMediaSource
import com.example.podcastapp.exoplayer.PodcastNotificationManager
import com.example.podcastapp.exoplayer.callback.PodcastPlaybackPreparer
import com.example.podcastapp.exoplayer.callback.PodcastPlayerNotificationListener
import com.example.podcastapp.util.consts.Constants.ACTION_PODCAST_NOTIFICATION_CLICK
import com.example.podcastapp.util.consts.Constants.MEDIA_ROOT_ID
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector
import com.google.android.exoplayer2.upstream.cache.CacheDataSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import javax.inject.Inject

@AndroidEntryPoint
class PodcastService @Inject constructor(
    private val dataSourceFactory: CacheDataSource.Factory,
    private val exoPlayer: ExoPlayer,
    private val podcastMediaSource: PodcastMediaSource
) : MediaBrowserServiceCompat() {

    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

    private lateinit var mediaSession: MediaSessionCompat
    private lateinit var mediaSessionConnector: MediaSessionConnector
    private lateinit var podcastNotificationManager: PodcastNotificationManager

    private var currentPlayingMedia: MediaMetadataCompat? = null

    private var isPlayerInitialized = false

    var isForeground = false

    companion object {
        private const val SERVICE_TAG = "PodcastService"

        var currentDuration: Long = 0L
            private set
    }

    override fun onCreate() {
        super.onCreate()

        val activityIntent = packageManager?.getLaunchIntentForPackage(packageName)?.let {
            it.action = ACTION_PODCAST_NOTIFICATION_CLICK
            PendingIntent.getActivity(
                this,
                0,
                it,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        }

        mediaSession = MediaSessionCompat(this, SERVICE_TAG).apply {
            setSessionActivity(activityIntent)
            isActive = true
        }

        sessionToken = mediaSession.sessionToken

        podcastNotificationManager = PodcastNotificationManager(
            this, mediaSession.sessionToken, PodcastPlayerNotificationListener(this)
        ) {
            currentDuration = exoPlayer.duration
        }

        val podcastPlaybackPreparer = PodcastPlaybackPreparer(podcastMediaSource) {
            currentPlayingMedia = it
            preparePlayer(
                podcastMediaSource.mediaMetadataEpisodes,
                it,
                true
            )
        }

        mediaSessionConnector = MediaSessionConnector(mediaSession).apply {
            setPlaybackPreparer(podcastPlaybackPreparer)
            setPlayer(exoPlayer)
        }
    }

    private fun preparePlayer(
        episodes: List<MediaMetadataCompat>,
        itemToPlay: MediaMetadataCompat?,
        playNow: Boolean
    ) {
        val indexToPlay = if (currentPlayingMedia == null) 0 else episodes.indexOf(itemToPlay)
        exoPlayer.setMediaSource(podcastMediaSource.asMediaSource(dataSourceFactory))
        exoPlayer.seekTo(indexToPlay, 0L)
        exoPlayer.playWhenReady = playNow
    }

    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ): BrowserRoot {
        return BrowserRoot(MEDIA_ROOT_ID, null)
    }

    override fun onLoadChildren(
        parentId: String,
        result: Result<MutableList<MediaBrowserCompat.MediaItem>>
    ) {
        when (parentId) {
            MEDIA_ROOT_ID -> {
                val resultsSent = podcastMediaSource.whenReady { isInitialized ->
                    if (isInitialized) {
                        result.sendResult(podcastMediaSource.asMediaItems())
                        if (!isPlayerInitialized && podcastMediaSource.mediaMetadataEpisodes.isNotEmpty()) {
                            isPlayerInitialized = true
                        }
                    } else {
                        result.sendResult(null)
                    }
                }
                if (!resultsSent)
                    result.detach()
            }
            else -> {}
        }
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        exoPlayer.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
        exoPlayer.release()
    }
}