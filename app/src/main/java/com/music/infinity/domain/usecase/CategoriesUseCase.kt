package com.music.infinity.domain.usecase

import arrow.core.Either
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.Album
import com.music.infinity.domain.model.Categories
import com.music.infinity.domain.repository.AlbumRepository
import com.music.infinity.domain.repository.CategoriesRepository

class CategoriesUseCase(private val categoriesRepository: CategoriesRepository) {

    suspend fun getCategories(): Either<Failure, List<Categories>> {
        return categoriesRepository.getCategories()
    }
}