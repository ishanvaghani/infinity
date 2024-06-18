package com.music.infinity.data.remote.dto

import com.music.infinity.domain.model.SearchList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchListDto(
    @SerialName("albums")
    val albums: AlbumListDto,
    @SerialName("artists")
    val artists: ArtistListDto,
    @SerialName("tracks")
    val tracks: TrackListDto,
    @SerialName("limit")
    val limit: Int,
    @SerialName("offset")
    val offset: Int,
    @SerialName("total")
    val total: Int
) {

    fun toSearchList(): SearchList {
        return SearchList(
            albums.toAlbumList(),
            artists.toArtistList(),
            tracks.toTrackList(),
            limit,
            offset,
            total
        )
    }
}
