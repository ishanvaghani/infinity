package com.music.infinity.domain.model

data class AlbumList(
    val albums: List<Album>,
    val limit: Int,
    val offset: Int,
    val total: Int
)