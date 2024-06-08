package com.music.infinity.data.repository

import arrow.core.Either
import com.music.infinity.data.remote.NetworkManager
import com.music.infinity.data.remote.SpotifyApi
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.Album
import com.music.infinity.domain.repository.AlbumRepository

class AlbumRepositoryImpl(
    private val spotifyApi: SpotifyApi,
    private val networkManager: NetworkManager
) : AlbumRepository {

    override suspend fun getNewReleasesAlbums(): Either<Failure, List<Album>> {
        return networkManager.executeWithAuthentication {
            spotifyApi.getNewReleasesAlbums(0)
        }
    }
}