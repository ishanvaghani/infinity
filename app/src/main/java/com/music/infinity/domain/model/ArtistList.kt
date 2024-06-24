package com.music.infinity.domain.model

data class ArtistList(
    val artists: List<Artist>,
    val limit: Int,
    val offset: Int,
    val total: Int
)