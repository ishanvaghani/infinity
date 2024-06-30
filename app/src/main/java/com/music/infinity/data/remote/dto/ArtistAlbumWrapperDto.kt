package com.music.infinity.data.remote.dto

import com.music.infinity.domain.model.ArtistAlbumWrapper
import kotlinx.serialization.SerialName

class ArtistAlbumWrapperDto(
    @SerialName("href")
    val href: String,
    @SerialName("limit")
    val limit: Long,
    @SerialName("next")
    val next: String,
    @SerialName("offset")
    val offset: Long,
    @SerialName("previous")
    val previous: Any?,
    @SerialName("total")
    val total: Long,
    @SerialName("items")
    val items: List<ArtistAlbumDto>,
) {

    fun toArtistAlbumWrapper() : ArtistAlbumWrapper {
        return  ArtistAlbumWrapper(
            href, limit, next, offset, previous, total, items.map {
                it.toArtistAlbum()
            }
        )
    }

}