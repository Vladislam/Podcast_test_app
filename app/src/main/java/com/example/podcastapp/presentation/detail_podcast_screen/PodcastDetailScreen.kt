package com.example.podcastapp.presentation.detail_podcast_screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun PodcastDetailScreen(
    podcastId: String,
    navigator: DestinationsNavigator,
    viewModel: PodcastDetailViewModel = hiltViewModel()
) {

}