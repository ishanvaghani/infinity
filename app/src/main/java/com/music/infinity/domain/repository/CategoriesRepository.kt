package com.music.infinity.domain.repository

import arrow.core.Either
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.Album
import com.music.infinity.domain.model.Categories

interface CategoriesRepository {
    suspend fun getCategories(): Either<Failure, List<Categories>>
}