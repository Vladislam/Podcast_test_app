package com.example.podcastapp.presentation.podcast_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.podcastapp.domain.model.best_podcast.BestPodcastsResponse
import com.example.podcastapp.domain.repository.PodcastRepository
import com.example.podcastapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PodcastViewModel @Inject constructor(
    private val repos: PodcastRepository
) : ViewModel() {

    var podcastState by mutableStateOf(PodcastState())
        private set

    private var bestPodcasts: BestPodcastsResponse? = null

    init {
        getBestPodcasts(false, "Ukrainian", 1)
    }

    private fun getBestPodcasts(
        fetchFromRemote: Boolean,
        language: String,
        page: Int
    ) = viewModelScope.launch {
        repos.getBestPodcasts(fetchFromRemote, page, language).collect { result ->
            when (result) {
                is Resource.Error -> {
                    podcastState = podcastState.copy(error = result.message)
                }
                is Resource.Loading -> {
                    podcastState = podcastState.copy(isLoading = result.isLoading)
                }
                is Resource.Success -> {
                    result.data?.let { bestPodcastsResponse ->
                        bestPodcasts = bestPodcastsResponse
                        podcastState = podcastState.copy(
                            bestPodcasts = podcastState.bestPodcasts.toMutableList()
                                .also { it.addAll(bestPodcastsResponse.podcasts) })
                    }
                }
            }
        }
    }
}