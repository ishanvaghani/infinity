package com.music.infinity.domain.model

data class TrackList(
    val tracks: List<Track>,
    val limit: Int,
    val offset: Int,
    val total: Int
)