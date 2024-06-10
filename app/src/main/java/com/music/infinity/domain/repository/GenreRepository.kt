package com.music.infinity.domain.repository

import arrow.core.Either
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.Categories
import com.music.infinity.domain.model.Genre

interface GenreRepository {
    suspend fun getGenre(): Either<Failure, Genre>
}