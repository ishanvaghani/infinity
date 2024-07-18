package com.music.infinity.data.repository

import arrow.core.Either
import com.music.infinity.data.remote.NetworkManager
import com.music.infinity.data.remote.SpotifyApi
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.Playlist
import com.music.infinity.domain.model.PlaylistList
import com.music.infinity.domain.repository.PlaylistRepository

class PlaylistRepositoryImpl(
    private val spotifyApi: SpotifyApi,
    private val networkManager: NetworkManager
) : PlaylistRepository {

    override suspend fun getFeaturedPlaylists(): Either<Failure, PlaylistList> {
        val response = networkManager.executeWithAuthentication {
            spotifyApi.getFeaturedPlaylists()
        }
        return response.map { it.toPlaylistList() }
    }

    override suspend fun getPlaylist(id: String): Either<Failure, Playlist> {
        val response = networkManager.executeWithAuthentication {
            spotifyApi.getPlaylist(id)
        }
        return response.map { it.toPlaylist() }
    }
}