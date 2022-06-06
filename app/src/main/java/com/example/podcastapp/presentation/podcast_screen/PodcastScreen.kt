package com.example.podcastapp.presentation.podcast_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.podcastapp.R
import com.example.podcastapp.destinations.PodcastDetailScreenDestination
import com.example.podcastapp.ui.common.BestPodcastItem
import com.example.podcastapp.ui.common.LargeTitle
import com.example.podcastapp.util.consts.Constants.BOTTOM_NAVIGATION_HEIGHT
import com.example.podcastapp.util.consts.Constants.GRID_COLUMNS
import com.example.podcastapp.util.extension.fullSpanItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
@RootNavGraph(start = true)
fun PodcastScreen(
    navigator: DestinationsNavigator,
    viewModel: PodcastViewModel = hiltViewModel()
) {
    val scrollState = rememberLazyGridState()
    val podcastState = viewModel.podcastState

    if (podcastState.error == null) {
        Surface {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(count = GRID_COLUMNS),
                state = scrollState
            ) {
                fullSpanItem {
                    LargeTitle(stringResource(R.string.trending_now))
                }
                items(podcastState.bestPodcasts.size) { i ->
                    val curPodcast = podcastState.bestPodcasts[i]
                    BestPodcastItem(
                        modifier = Modifier.padding(16.dp),
                        podcast = curPodcast
                    ) {
                        navigator.navigate(PodcastDetailScreenDestination(curPodcast.id))
                    }
                }
                fullSpanItem {
                    Spacer(modifier = Modifier.height(BOTTOM_NAVIGATION_HEIGHT))
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        if (podcastState.isLoading) {
            CircularProgressIndicator()
        } else if (podcastState.error != null) {
            Text(text = podcastState.error.asString(), color = MaterialTheme.colors.error)
        }
    }
}