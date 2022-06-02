package com.example.podcastapp.data.mapper

import com.example.podcastapp.data.local.best_podcast.BestPodcastEntity
import com.example.podcastapp.data.remote.dto.best_podcasts.BestPodcastDto
import com.example.podcastapp.data.remote.dto.best_podcasts.BestPodcastsResponseDto
import com.example.podcastapp.data.remote.dto.search_podcast.EpisodeDto
import com.example.podcastapp.data.remote.dto.search_podcast.PodcastDto
import com.example.podcastapp.data.remote.dto.search_podcast.PodcastSearchDto
import com.example.podcastapp.domain.model.podcast.*

fun EpisodeDto.toEpisode(): Episode = Episode(
    audio = audio,
    audioLengthSec = audioLengthSec,
    descriptionOriginal = descriptionOriginal,
    explicitContent = explicitContent,
    id = id,
    image = image,
    link = link,
    listenNotesUrl = listenNotesUrl,
    podcast = podcast.toPodcast(),
    pubDateMs = pubDateMs,
    thumbnail = thumbnail,
    titleOriginal = titleOriginal,
)

fun PodcastDto.toPodcast(): Podcast = Podcast(
    id = id,
    image = image,
    listenNotesUrl = listenNotesUrl,
    publisherHighlighted = publisherHighlighted,
    publisherOriginal = publisherOriginal,
    thumbnail = thumbnail,
    titleOriginal = titleOriginal
)

fun PodcastSearchDto.toPodcastSearch(): PodcastSearch = PodcastSearch(
    count = count,
    nextOffset = nextOffset,
    episodes = episodes.map { it.toEpisode() },
    total = total
)

fun BestPodcastEntity.toBestPodcast(): BestPodcast = BestPodcast(
    country = country,
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

fun BestPodcastDto.toBestPodcast(): BestPodcast = BestPodcast(
    country = country,
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

fun BestPodcastDto.toBestPodcastEntity(): BestPodcastEntity = BestPodcastEntity(
    country = country,
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