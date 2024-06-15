package com.music.infinity.data.remote.dto


import com.music.infinity.domain.model.Track
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrackDto(
    @SerialName("artists")
    val artists: List<ArtistDto>,
    @SerialName("disc_number")
    val discNumber: Int,
    @SerialName("duration_ms")
    val durationMs: Int,
    @SerialName("href")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("preview_url")
    val previewUrl: String,
    @SerialName("track_number")
    val trackNumber: Int,
) {

    fun toTrack(): Track {
        return Track(
            artists.map { it.toArtist() },
            discNumber,
            durationMs,
            id,
            name,
            previewUrl,
            trackNumber
        )
    }
}