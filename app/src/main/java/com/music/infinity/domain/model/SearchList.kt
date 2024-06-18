package com.music.infinity.domain.model

data class SearchList(
    val albums: AlbumList,
    val artists: ArtistList,
    val tracks: TrackList,
    val limit: Int,
    val offset: Int,
    val total: Int
)
