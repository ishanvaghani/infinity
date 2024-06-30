package com.music.infinity.data.remote.dto

import com.music.infinity.domain.model.RelatedArtist

data class RelatedArtistDto(
    val artists: List<ArtistInfoDto>,
) {
    fun toRelatedArtistList(): RelatedArtist {
        return RelatedArtist(artists.map { it.toArtistInfo() })
    }
}