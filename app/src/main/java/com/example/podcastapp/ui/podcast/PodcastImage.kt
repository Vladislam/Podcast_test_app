package com.example.podcastapp.ui.podcast

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.podcastapp.R

@Composable
fun PodcastImage(
    url: String,
    modifier: Modifier = Modifier,
    aspectRatio: Float = 1f,
) {
    val imagePainter = rememberAsyncImagePainter(url)

    Box(
        modifier
            .clip(MaterialTheme.shapes.medium)
            .aspectRatio(aspectRatio)
            .background(MaterialTheme.colors.onBackground.copy(alpha = 0.08f))
    ) {
        Image(
            painter = imagePainter,
            contentDescription = stringResource(R.string.podcast_thumbnail),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        when (imagePainter.state) {
            is AsyncImagePainter.State.Success -> {
                // Remove placeholder
            }
            else -> {
                Icon(
                    painter = painterResource(R.drawable.ic_microphone),
                    contentDescription = stringResource(R.string.podcast_thumbnail),
                    tint = MaterialTheme.colors.onBackground.copy(alpha = 0.14f),
                    modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.Center),
                )
            }
        }
    }
}

@Composable
fun PodcastPoster(
    url: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = stringResource(R.string.podcast_thumbnail),
        placeholder = painterResource(R.drawable.ic_microphone),
        error = painterResource(R.drawable.ic_microphone),
        fallback = painterResource(R.drawable.ic_microphone),
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .aspectRatio(1f)
    )
}