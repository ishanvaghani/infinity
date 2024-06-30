package com.music.infinity.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import arrow.core.Either
import com.music.infinity.data.remote.NetworkConstant
import com.music.infinity.data.remote.NetworkManager
import com.music.infinity.data.remote.SpotifyApi
import com.music.infinity.data.remote.datasource.NewReleasedAlbumsDataSource
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.Album
import com.music.infinity.domain.model.AlbumDetail
import com.music.infinity.domain.model.AlbumList
import com.music.infinity.domain.model.TrackList
import com.music.infinity.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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

    override suspend fun getNewReleasesAlbumsPaging(): Flow<PagingData<Album>> {
        val result = Pager(
            config = PagingConfig(pageSize = NetworkConstant.PAGE_LIMIT, maxSize = 100),
            pagingSourceFactory = {
                NewReleasedAlbumsDataSource(spotifyApi, networkManager)
            }
        ).flow
        return result.map { pagingData -> pagingData.map { albumDto -> albumDto.toAlbum() } }
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