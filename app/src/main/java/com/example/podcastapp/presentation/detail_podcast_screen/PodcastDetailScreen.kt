package com.example.podcastapp.presentation.detail_podcast_screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.podcastapp.ui.common.EpisodeItem
import com.example.podcastapp.util.consts.Constants
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

        }

        items(podcastState.podcast.episodes.size) { i ->
            EpisodeItem(episode = podcastState.podcast.episodes[i], onClick = {})
        }
    }
}