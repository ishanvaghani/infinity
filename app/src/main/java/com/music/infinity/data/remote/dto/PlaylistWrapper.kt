package com.music.infinity.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaylistWrapper(
    @SerialName("playlists")
    val playlists: PlaylistListDto
)
