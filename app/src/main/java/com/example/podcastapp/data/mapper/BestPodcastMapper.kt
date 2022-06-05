package com.example.podcastapp.data.mapper

import com.example.podcastapp.data.local.best_podcast.BestPodcastEntity
import com.example.podcastapp.data.remote.dto.best_podcasts.BestPodcastDto
import com.example.podcastapp.data.remote.dto.best_podcasts.BestPodcastsResponseDto
import com.example.podcastapp.domain.model.best_podcast.BestPodcast
import com.example.podcastapp.domain.model.best_podcast.BestPodcastsResponse


fun BestPodcastEntity.toBestPodcast(): BestPodcast = BestPodcast(
    description = description,
    website = website,
    id = id,
    image = image,
    language = language,
    publisher = publisher,
    thumbnail = thumbnail,
    title = title,
    totalEpisodes = totalEpisodes,
    earliestPubDateMs = earliestPubDateMs,
    explicitContent = explicitContent,
    audioLengthSec = audioLengthSec,
    listenNotesUrl = listenNotesUrl,
    country = country
)

fun BestPodcastDto.toBestPodcast(): BestPodcast = BestPodcast(
    description = description,
    website = website,
    id = id,
    image = image,
    language = language,
    publisher = publisher,
    thumbnail = thumbnail,
    title = title,
    totalEpisodes = totalEpisodes,
    earliestPubDateMs = earliestPubDateMs,
    explicitContent = explicitContent,
    audioLengthSec = audioLengthSec,
    listenNotesUrl = listenNotesUrl,
    country = country
)

fun BestPodcastDto.toBestPodcastEntity(): BestPodcastEntity = BestPodcastEntity(
    description = description,
    website = website,
    id = id,
    image = image,
    language = language,
    publisher = publisher,
    thumbnail = thumbnail,
    title = title,
    totalEpisodes = totalEpisodes,
    earliestPubDateMs = earliestPubDateMs,
    explicitContent = explicitContent,
    audioLengthSec = audioLengthSec,
    listenNotesUrl = listenNotesUrl,
)

fun BestPodcastsResponseDto.toBestPodcasts(): BestPodcastsResponse = BestPodcastsResponse(
    podcasts = podcasts.map { it.toBestPodcast() },
    id = id,
    name = name,
    total = total,
    hasNext = hasNext,
    hasPrevious = hasPrevious,
    listenNotesUrl = listenNotesUrl,
    nextPageNumber = nextPageNumber,
    pageNumber = pageNumber,
    previousPageNumber = previousPageNumber
)