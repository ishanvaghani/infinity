package com.music.infinity.domain.model

import com.music.infinity.common.model.Image
import kotlinx.serialization.SerialName

data class AlbumDetail(
    @SerialName("album_type")
    val albumType: String,
    @SerialName("artists")
    val artists: List<Artist>,
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
    val track: TrackList
)