package com.example.podcastapp.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.podcastapp.R
import com.example.podcastapp.domain.model.best_podcast.BestPodcast
import com.example.podcastapp.domain.model.podcast.Episode

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


@Composable
fun EpisodeItem(
    episode: Episode,
    modifier: Modifier = Modifier,
    onClick: (episode: Episode) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .clickable { onClick(episode) }
            .padding(16.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(episode.thumbnail)
                .crossfade(true)
                .build(),
            contentDescription = episode.title,
            placeholder = painterResource(R.drawable.ic_microphone),
            error = painterResource(R.drawable.ic_microphone),
            fallback = painterResource(R.drawable.ic_microphone),
            modifier = Modifier
                .size(46.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colors.onBackground.copy(alpha = 0.08f))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = episode.title,
                maxLines = 1,
                style = MaterialTheme.typography.h2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = episode.description.replace(Regex("<.*?>"), ""),
                maxLines = 1,
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}