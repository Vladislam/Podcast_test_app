package com.example.podcastapp.presentation.detail_podcast_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.podcastapp.R
import com.example.podcastapp.domain.model.podcast.Podcast
import com.example.podcastapp.ui.common.EmphasisText
import com.example.podcastapp.ui.common.EpisodeItem
import com.example.podcastapp.ui.common.ExpandableText
import com.example.podcastapp.ui.common.PodcastPoster
import com.example.podcastapp.util.formatMillisecondsAsDate
import com.example.podcastapp.util.toDurationMinutes
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun PodcastDetailScreen(
    podcastId: String,
    navigator: DestinationsNavigator,
    viewModel: PodcastDetailViewModel = hiltViewModel()
) {
    val podcastState = viewModel.podcastState
    val scrollState = rememberLazyListState()

    LazyColumn(modifier = Modifier.fillMaxSize(), state = scrollState) {
        item {
            PodcastHeader(podcast = podcastState.podcast)
        }

        items(podcastState.podcast.episodes.size) { i ->
            EpisodeItem(episode = podcastState.podcast.episodes[i], onClick = {})
        }
    }
}

@Composable
fun PodcastHeader(podcast: Podcast) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        PodcastPoster(
            url = podcast.image,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .align(CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = podcast.title, style = MaterialTheme.typography.h1)
            Icon(
                imageVector = Icons.Rounded.Info,
                contentDescription = stringResource(R.string.share),
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        //TODO: Open the Listen Notes
                    }
            )
        }

        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = podcast.publisher,
            style = MaterialTheme.typography.body1
        )

        EmphasisText(
            text = "${podcast.firstPubDateMs.formatMillisecondsAsDate("MMM dd")} • " +
                    podcast.audioLengthSec.toDurationMinutes()
        )

        Spacer(modifier = Modifier.height(8.dp))

        ExpandableText(text = podcast.description)
    }
}