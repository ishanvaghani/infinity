package com.music.infinity.data.remote.dto

import com.music.infinity.domain.model.TrackList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrackListDto(
    @SerialName("items")
    val tracks: List<TrackDto>,
    @SerialName("limit")
    val limit: Int,
    @SerialName("offset")
    val offset: Int,
    @SerialName("total")
    val total: Int
) {

    fun toTrackList(): TrackList {
        return TrackList(
            tracks.map { it.toTrack() },
            limit,
            offset,
            total
        )
    }
}