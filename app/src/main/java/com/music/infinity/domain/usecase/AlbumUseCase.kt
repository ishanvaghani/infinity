package com.music.infinity.domain.usecase

import arrow.core.Either
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.Album
import com.music.infinity.domain.repository.AlbumRepository

class AlbumUseCase(private val albumRepository: AlbumRepository) {

    suspend fun getNewReleasesAlbums(): Either<Failure, List<Album>> {
        return albumRepository.getNewReleasesAlbums()
    }
}