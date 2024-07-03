package com.music.infinity.data.repository

import arrow.core.Either
import com.music.infinity.data.remote.NetworkManager
import com.music.infinity.data.remote.SpotifyApi
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.AlbumDetail
import com.music.infinity.domain.model.AlbumList
import com.music.infinity.domain.model.TrackList
import com.music.infinity.domain.repository.AlbumRepository

class AlbumRepositoryImpl(
    private val spotifyApi: SpotifyApi,
    private val networkManager: NetworkManager
) : AlbumRepository {

    override suspend fun getNewReleasesAlbums(): Either<Failure, AlbumList> {
        val result = networkManager.executeWithAuthentication {
            spotifyApi.getNewReleasesAlbums(0)
        }
        return result.map { it.toAlbumList() }
    }

    override suspend fun getAlbum(id: String): Either<Failure, AlbumDetail> {
        val result = networkManager.executeWithAuthentication {
            spotifyApi.getAlbum(id)
        }
        return result.map { it.toAlbumDetail() }
    }

    override suspend fun getAlbumTracks(id: String, offset: Int): Either<Failure, TrackList> {
        val result = networkManager.executeWithAuthentication {
            spotifyApi.getAlbumTracks(id, offset)
        }
        return result.map { it.toTrackList() }
    }
}