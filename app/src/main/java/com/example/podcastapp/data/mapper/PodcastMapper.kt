package com.example.podcastapp.data.mapper

import com.example.podcastapp.data.remote.dto.podcast.EpisodeDto
import com.example.podcastapp.data.remote.dto.search_podcast.SearchPodcastDto
import com.example.podcastapp.data.remote.dto.search_podcast.SearchPodcastsResponseDto
import com.example.podcastapp.domain.model.podcast.Episode
import com.example.podcastapp.domain.model.podcast.SearchPodcast
import com.example.podcastapp.domain.model.podcast.SearchPodcastsResponse

fun EpisodeDto.toEpisode(): Episode = Episode(
    audio = audio,
    audioLengthSec = audioLengthSec,
    explicitContent = explicitContent,
    id = id,
    image = image,
    pubDateMs = pubDateMs,
    thumbnail = thumbnail,
    title = title,
    maybeAudioInvalid = maybeAudioInvalid,
    description = description
)

fun SearchPodcastDto.toSearchPodcast(): SearchPodcast = SearchPodcast(
    id = id,
    image = image,
    listenNotesUrl = listenNotesUrl,
    publisherHighlighted = publisherHighlighted,
    publisherOriginal = publisherOriginal,
    thumbnail = thumbnail,
    titleOriginal = titleOriginal
)

fun SearchPodcastsResponseDto.toPodcastSearch(): SearchPodcastsResponse = SearchPodcastsResponse(
    count = count,
    nextOffset = nextOffset,
    podcasts = podcasts.map { it.toSearchPodcast() },
    total = total
)