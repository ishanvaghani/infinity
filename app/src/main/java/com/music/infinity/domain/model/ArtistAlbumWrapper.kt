package com.music.infinity.domain.model

data class ArtistAlbumWrapper(
    val href: String,
    val limit: Long,
    val next: String,
    val offset: Long,
    val previous: Any?,
    val total: Long,
    val items: List<ArtistAlbum>,
)