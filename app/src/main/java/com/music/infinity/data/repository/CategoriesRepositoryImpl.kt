package com.music.infinity.data.repository

import arrow.core.Either
import com.music.infinity.data.remote.NetworkManager
import com.music.infinity.data.remote.SpotifyApi
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.CategoriesList
import com.music.infinity.domain.model.Category
import com.music.infinity.domain.repository.CategoriesRepository

class CategoriesRepositoryImpl(
    private val spotifyApi: SpotifyApi,
    private val networkManager: NetworkManager
) : CategoriesRepository {

    override suspend fun getCategories(): Either<Failure, CategoriesList> {
        val response = networkManager.executeWithAuthentication {
            spotifyApi.getCategories(0)
        }
        return response.map { it.toCategoriesList() }
    }

    override suspend fun getCategory(id: String): Either<Failure, Category> {
        val response = networkManager.executeWithAuthentication {
            spotifyApi.getCategory(id)
        }
        return response.map { it.toCategory() }
    }
}