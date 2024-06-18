package com.music.infinity.data.remote.dto

import com.music.infinity.domain.model.ArtistList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistListDto(
    @SerialName("items")
    val artists: List<ArtistDto>,
    @SerialName("limit")
    val limit: Int,
    @SerialName("offset")
    val offset: Int,
    @SerialName("total")
    val total: Int
) {

    fun toArtistList(): ArtistList {
        return ArtistList(
            artists.map { it.toArtist() },
            limit,
            offset,
            total
        )
    }
}