package com.example.podcastapp.data.mapper

import com.example.podcastapp.data.local.episodes.EpisodeEntity
import com.example.podcastapp.data.local.episodes.PodcastEntity
import com.example.podcastapp.data.local.episodes.PodcastWithEpisodes
import com.example.podcastapp.data.remote.dto.podcast.EpisodeDto
import com.example.podcastapp.data.remote.dto.podcast.PodcastResponseDto
import com.example.podcastapp.data.remote.dto.search_podcast.SearchPodcastDto
import com.example.podcastapp.data.remote.dto.search_podcast.SearchPodcastsResponseDto
import com.example.podcastapp.domain.model.podcast.Episode
import com.example.podcastapp.domain.model.podcast.Podcast
import com.example.podcastapp.domain.model.search_podcast.SearchPodcast
import com.example.podcastapp.domain.model.search_podcast.SearchPodcastsResponse

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

fun EpisodeDto.toEpisodeEntity(podcastId: String): EpisodeEntity = EpisodeEntity(
    audio = audio,
    audioLengthSec = audioLengthSec,
    explicitContent = explicitContent,
    id = id,
    image = image,
    pubDateMs = pubDateMs,
    thumbnail = thumbnail,
    title = title,
    maybeAudioInvalid = maybeAudioInvalid,
    description = description,
    podcastId = podcastId,
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

fun EpisodeEntity.toEpisode(): Episode = Episode(
    audio = audio,
    id = id,
    image = image,
    thumbnail = thumbnail,
    title = title,
    maybeAudioInvalid = maybeAudioInvalid,
    pubDateMs = pubDateMs,
    audioLengthSec = audioLengthSec,
    description = description,
    explicitContent = explicitContent
)

fun PodcastWithEpisodes.toPodcast(): Podcast = Podcast(
    id = this.podcast.id,
    description = podcast.description,
    thumbnail = podcast.thumbnail,
    title = podcast.title,
    episodes = episodes.map { it.toEpisode() },
    image = podcast.image,
    language = podcast.language,
    explicitContent = podcast.explicitContent,
    nextEpisodePubDate = podcast.nextEpisodePubDate,
    totalEpisodes = podcast.totalEpisodes,
    audioLengthSec = podcast.audioLengthSec
)

fun PodcastResponseDto.toPodcast(): Podcast = Podcast(
    id = id,
    description = description,
    thumbnail = thumbnail,
    title = title,
    episodes = episodes.map { it.toEpisode() },
    image = image,
    language = language,
    explicitContent = explicitContent,
    nextEpisodePubDate = nextEpisodePubDate,
    totalEpisodes = totalEpisodes,
    audioLengthSec = audioLengthSec
)

fun PodcastResponseDto.toPodcastEntity(): PodcastEntity = PodcastEntity(
    id = id,
    description = description,
    thumbnail = thumbnail,
    title = title,
    image = image,
    language = language,
    explicitContent = explicitContent,
    nextEpisodePubDate = nextEpisodePubDate,
    totalEpisodes = totalEpisodes,
    audioLengthSec = audioLengthSec
)