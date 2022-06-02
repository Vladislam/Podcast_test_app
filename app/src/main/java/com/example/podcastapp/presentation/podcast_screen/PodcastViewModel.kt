package com.example.podcastapp.presentation.podcast_screen

import androidx.lifecycle.ViewModel
import com.example.podcastapp.domain.repository.PodcastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PodcastViewModel @Inject constructor(
    private val repos: PodcastRepository
) : ViewModel() {

}