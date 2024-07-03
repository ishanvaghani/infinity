package com.music.infinity.data.remote.dto

import com.music.infinity.domain.model.AlbumList
import com.music.infinity.domain.model.PlaylistList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaylistListDto(
    @SerialName("items")
    val playlistList: List<PlaylistDto>,
    @SerialName("limit")
    val limit: Int,
    @SerialName("offset")
    val offset: Int,
    @SerialName("total")
    val total: Int
) {

    fun toPlaylistList(): PlaylistList {
        return PlaylistList(
            playlistList.map { it.toPlaylist() },
            limit,
            offset,
            total
        )
    }
}