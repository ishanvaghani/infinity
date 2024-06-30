package com.music.infinity.domain.repository

import androidx.paging.PagingData
import arrow.core.Either
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.Album
import com.music.infinity.domain.model.AlbumDetail
import com.music.infinity.domain.model.AlbumList
import com.music.infinity.domain.model.TrackList
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {

    suspend fun getNewReleasesAlbums(): Either<Failure, AlbumList>

    suspend fun getNewReleasesAlbumsPaging(): Flow<PagingData<Album>>

    suspend fun getAlbum(id: String): Either<Failure, AlbumDetail>

    suspend fun getAlbumTracks(id: String, offset: Int): Either<Failure, TrackList>
}