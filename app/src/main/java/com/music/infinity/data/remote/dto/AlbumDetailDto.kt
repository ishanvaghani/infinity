package com.music.infinity.data.remote.dto


import com.music.infinity.common.model.Image
import com.music.infinity.domain.model.AlbumDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumDetailDto(
    @SerialName("album_type")
    val albumType: String,
    @SerialName("artists")
    val artists: List<ArtistDto>,
    @SerialName("genres")
    val genres: List<String>,
    @SerialName("id")
    val id: String,
    @SerialName("images")
    val images: List<Image>,
    @SerialName("label")
    val label: String,
    @SerialName("name")
    val name: String,
    @SerialName("popularity")
    val popularity: Int,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("release_date_precision")
    val releaseDatePrecision: String,
    @SerialName("total_tracks")
    val totalTracks: Int,
    @SerialName("tracks")
    val tracks: TrackListDto
) {

    fun toAlbumDetail(): AlbumDetail {
        return AlbumDetail(
            albumType,
            artists.map { it.toArtist() },
            genres,
            id,
            images,
            label,
            name,
            popularity,
            releaseDate,
            releaseDatePrecision,
            totalTracks,
            tracks.toTrackList()
        )
    }
}