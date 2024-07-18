package com.music.infinity.data.remote.dto

import com.music.infinity.common.model.Image
import com.music.infinity.domain.model.Playlist
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaylistDto(
    @SerialName("description")
    val description: String,
    @SerialName("href")
    val href: String,
    @SerialName("id")
    val id: String,
    @SerialName("images")
    val images: List<Image>,
    @SerialName("name")
    val name: String,
    @SerialName("followers")
    val followers: FollowersDto,
    @SerialName("tracks")
    val tracks: List<TrackDto>
) {

    fun toPlaylist(): Playlist {
        return Playlist(
            description,
            href,
            id,
            images,
            name,
            followers.toFollower(),
            tracks.map { it.toTrack() }
        )
    }
}
