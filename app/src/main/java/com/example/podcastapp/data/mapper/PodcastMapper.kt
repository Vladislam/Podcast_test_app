package com.example.podcastapp.data.mapper

import com.example.podcastapp.data.remote.dto.EpisodeDto
import com.example.podcastapp.data.remote.dto.PodcastDto
import com.example.podcastapp.data.remote.dto.PodcastSearchDto
import com.example.podcastapp.domain.model.Episode
import com.example.podcastapp.domain.model.Podcast
import com.example.podcastapp.domain.model.PodcastSearch

fun EpisodeDto.toEpisode(): Episode = Episode(
    audio = audio,
    audioLengthSec = audioLengthSec,
    descriptionHighlighted = descriptionHighlighted,
    descriptionOriginal = descriptionOriginal,
    explicitContent = explicitContent,
    guidFromRss = guidFromRss,
    id = id,
    image = image,
    itunesId = itunesId,
    link = link,
    listenNotesUrl = listenNotesUrl,
    podcast = podcast.toPodcast(),
    pubDateMs = pubDateMs,
    rss = rss,
    thumbnail = thumbnail,
    titleHighlighted = titleHighlighted,
    titleOriginal = titleOriginal,
    transcriptsHighlighted = transcriptsHighlighted
)

fun PodcastDto.toPodcast(): Podcast = Podcast(
    genreIds = genreIds,
    id = id,
    image = image,
    listenScore = listenScore,
    listenScoreGlobalRank = listenScoreGlobalRank,
    listenNotesUrl = listenNotesUrl,
    publisherHighlighted = publisherHighlighted,
    publisherOriginal = publisherOriginal,
    thumbnail = thumbnail,
    titleHighlighted = titleHighlighted,
    titleOriginal = titleOriginal
)

fun PodcastSearchDto.toPodcastSearch(): PodcastSearch = PodcastSearch(
    count = count,
    nextOffset = nextOffset,
    episodes = episodes.map { it.toEpisode() },
    took = took,
    total = total
)