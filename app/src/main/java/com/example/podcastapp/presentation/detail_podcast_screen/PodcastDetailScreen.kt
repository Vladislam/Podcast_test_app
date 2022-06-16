package com.example.podcastapp.presentation.detail_podcast_screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.podcastapp.R
import com.example.podcastapp.domain.model.podcast.Podcast
import com.example.podcastapp.ui.common.BackButton
import com.example.podcastapp.ui.common.CustomIconButton
import com.example.podcastapp.ui.common.EmphasisText
import com.example.podcastapp.ui.common.ExpandableText
import com.example.podcastapp.ui.podcast.EpisodeItem
import com.example.podcastapp.ui.podcast.PodcastPoster
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

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = scrollState
    ) {
        item {
            PodcastHeader(podcast = podcastState.podcast, navigator = navigator)
        }

        items(podcastState.podcast.episodes.size) { i ->
            EpisodeItem(episode = podcastState.podcast.episodes[i], onClick = {})
        }
    }
}

@Composable
fun PodcastHeader(
    podcast: Podcast,
    navigator: DestinationsNavigator
) {
    val context = LocalContext.current

    Column {
        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BackButton(navigator, modifier = Modifier.weight(1f))
            PodcastPoster(
                url = podcast.image,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(0.7f)
            )
            CustomIconButton(
                imageVector = Icons.Rounded.Share,
                contentDescription = stringResource(id = R.string.share),
                modifier = Modifier.weight(1f)
            ) {
                val text = context.getString(
                    R.string.share_podcast_content,
                    podcast.title,
                    podcast.listenNotesUrl
                )
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TITLE, podcast.title)
                    putExtra(Intent.EXTRA_TEXT, text)
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                context.startActivity(shareIntent)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = podcast.title,
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier.weight(1f)
                )
                CustomIconButton(
                    imageVector = Icons.Rounded.Info,
                    contentDescription = stringResource(R.string.info),
                ) {
                    val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(podcast.website))
                    context.startActivity(webIntent)
                }
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
}