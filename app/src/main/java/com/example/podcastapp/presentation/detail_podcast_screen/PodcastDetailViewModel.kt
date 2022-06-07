package com.example.podcastapp.presentation.detail_podcast_screen

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.podcastapp.domain.model.podcast.Podcast
import com.example.podcastapp.domain.repository.PodcastRepository
import com.example.podcastapp.util.Resource
import com.example.podcastapp.util.consts.Constants.PODCAST_ID_SAVED_STATE_TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PodcastDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: PodcastRepository
) : ViewModel() {

    var podcastState by mutableStateOf(PodcastDetailState())

    init {
        loadPodcastWithEpisodes(false, null)
    }

    private fun loadPodcastWithEpisodes(
        fetchFromRemote: Boolean,
        nextEpisodePubDate: Long?,
    ) = viewModelScope.launch {
        val podcastId = savedStateHandle.get<String>(PODCAST_ID_SAVED_STATE_TAG) ?: return@launch
        repository.getPodcastWithEpisodes(fetchFromRemote, podcastId, nextEpisodePubDate)
            .collect { result ->
                when (result) {
                    is Resource.Error -> {
                        podcastState = podcastState.copy(error = result.message)
                    }
                    is Resource.Loading -> {
                        podcastState = podcastState.copy(isLoading = result.isLoading)
                    }
                    is Resource.Success -> {
                        result.data?.let { podcast ->
                            podcastState = podcastState.copy(podcast = podcast)
                        }
                    }
                }
            }
    }
}
