package com.example.podcastapp.presentation.podcast_screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
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

}