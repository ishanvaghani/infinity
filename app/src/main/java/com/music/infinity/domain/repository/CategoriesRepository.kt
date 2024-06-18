package com.music.infinity.domain.repository

import arrow.core.Either
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.CategoriesList
import com.music.infinity.domain.model.Category

interface CategoriesRepository {
    suspend fun getCategories(): Either<Failure, CategoriesList>

    suspend fun getCategory(id: String): Either<Failure, Category>
}