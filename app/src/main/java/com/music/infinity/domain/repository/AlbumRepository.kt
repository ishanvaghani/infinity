package com.music.infinity.domain.repository

import arrow.core.Either
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.Album

interface AlbumRepository {

    suspend fun getNewReleasesAlbums(): Either<Failure, List<Album>>
}