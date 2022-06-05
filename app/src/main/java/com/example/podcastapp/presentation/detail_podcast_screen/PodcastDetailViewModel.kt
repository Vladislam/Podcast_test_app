package com.example.podcastapp.presentation.detail_podcast_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.podcastapp.domain.repository.PodcastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PodcastDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: PodcastRepository
) : ViewModel() {

}
