package com.music.infinity.domain.usecase

import androidx.paging.PagingData
import arrow.core.Either
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.Album
import com.music.infinity.domain.model.AlbumDetail
import com.music.infinity.domain.model.AlbumList
import com.music.infinity.domain.model.TrackList
import com.music.infinity.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow

class AlbumUseCase(private val albumRepository: AlbumRepository) {

    suspend fun getNewReleasesAlbums(): Either<Failure, AlbumList> {
        return albumRepository.getNewReleasesAlbums()
    }

    suspend fun getNewReleasesAlbumsPaging(): Flow<PagingData<Album>> {
        return albumRepository.getNewReleasesAlbumsPaging()
    }

    suspend fun getAlbum(id: String): Either<Failure, AlbumDetail> {
        return albumRepository.getAlbum(id)
    }

    suspend fun getAlbumTracks(id: String, offset: Int): Either<Failure, TrackList> {
        return albumRepository.getAlbumTracks(id, offset)
    }
}