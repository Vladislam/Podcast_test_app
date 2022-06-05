package com.example.podcastapp.presentation.podcast_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.podcastapp.domain.model.best_podcast.BestPodcast
import com.example.podcastapp.ui.common.PodcastImage

@Composable
fun BestPodcastItem(
    podcast: BestPodcast,
    modifier: Modifier = Modifier,
    onClick: (podcastId: String) -> Unit
) {
    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colors.background)
            .clickable(onClick = { onClick(podcast.id) }),
    ) {
        PodcastImage(
            url = podcast.thumbnail,
            aspectRatio = 1f
        )
        Text(
            text = podcast.title,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(8.dp)
        )
    }
}
