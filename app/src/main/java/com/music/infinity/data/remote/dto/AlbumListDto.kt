package com.music.infinity.data.remote.dto

import com.music.infinity.domain.model.AlbumList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumListDto(
    @SerialName("items")
    val albums: List<AlbumDto>,
    @SerialName("limit")
    val limit: Int,
    @SerialName("offset")
    val offset: Int,
    @SerialName("total")
    val total: Int
) {

    fun toAlbumList(): AlbumList {
        return AlbumList(
            albums.map { it.toAlbum() },
            limit,
            offset,
            total
        )
    }
}