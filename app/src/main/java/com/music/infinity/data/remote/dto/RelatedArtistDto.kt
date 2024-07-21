package com.music.infinity.data.remote.dto

import com.music.infinity.domain.model.RelatedArtist
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RelatedArtistDto(
    @SerialName("artists")
    val artists: List<ArtistInfoDto>,
) {
    fun toRelatedArtistList(): RelatedArtist {
        return RelatedArtist(artists.map { it.toArtistInfo() })
    }
}