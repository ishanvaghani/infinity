package com.music.infinity.data.remote.dto

import com.music.infinity.common.model.Image
import com.music.infinity.domain.model.Album
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumDto(
    @SerialName("album_type")
    val albumType: String,
    @SerialName("artists")
    val artistDtos: List<ArtistDto>,
    @SerialName("id")
    val id: String,
    @SerialName("images")
    val images: List<Image>,
    @SerialName("name")
    val name: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("total_tracks")
    val totalTracks: Int
) {

    fun toAlbum(): Album {
        return Album(
            albumType,
            artistDtos.map { it.toArtist() },
            id,
            images,
            name,
            releaseDate,
            totalTracks
        )
    }
}