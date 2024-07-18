package com.music.infinity.domain.model

data class PlaylistList(
    val playlists: List<Playlist>,
    val limit: Int,
    val offset: Int,
    val total: Int
)