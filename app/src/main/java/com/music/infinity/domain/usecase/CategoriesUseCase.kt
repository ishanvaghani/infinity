package com.music.infinity.domain.usecase

import arrow.core.Either
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.CategoriesList
import com.music.infinity.domain.model.Category
import com.music.infinity.domain.repository.CategoriesRepository

class CategoriesUseCase(private val categoriesRepository: CategoriesRepository) {

    suspend fun getCategories(): Either<Failure, CategoriesList> {
        return categoriesRepository.getCategories()
    }

    suspend fun getCategory(id: String): Either<Failure, Category> {
        return categoriesRepository.getCategory(id)
    }
}