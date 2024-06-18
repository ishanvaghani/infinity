package com.music.infinity.domain.model

data class ArtistList(
    val albums: List<Artist>,
    val limit: Int,
    val offset: Int,
    val total: Int
)