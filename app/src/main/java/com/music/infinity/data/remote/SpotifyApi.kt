package com.music.infinity.data.remote

import arrow.core.Either
import com.music.infinity.BuildConfig
import com.music.infinity.common.encodeBase64
import com.music.infinity.common.toCustomExceptions
import com.music.infinity.data.remote.dto.AlbumWrapperDto
import com.music.infinity.data.remote.dto.AuthTokenDto
import com.music.infinity.data.remote.dto.CategoriesWrapperDto
import com.music.infinity.data.remote.dto.GenresDto
import com.music.infinity.data.remote.model.BaseResponse
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.Album
import com.music.infinity.domain.model.Categories
import com.music.infinity.domain.model.Genre
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol

class SpotifyApi(private val client: HttpClient) {

    suspend fun getAccessToken(): Either<Failure, String> {
        try {
            val response = client.post(HttpRoutes.AUTH_TOKEN) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = NetworkConstant.AUTH_BASE_URL
                }
                val clientToken =
                    "${BuildConfig.CLIENT_ID}:${BuildConfig.CLIENT_SECRET}".encodeBase64()
                headers {
                    append(
                        HttpHeaders.Authorization,
                        "Basic $clientToken"
                    )
                    append(HttpHeaders.ContentType, "application/x-www-form-urlencoded")
                }
                setBody("grant_type=client_credentials")
            }
            return Either.Right(response.body<AuthTokenDto>().accessToken)
        } catch (e: Exception) {
            return Either.Left(e.toCustomExceptions())
        }
    }

    suspend fun getNewReleasesAlbums(offset: Int): Either<Failure, List<Album>> {
        return try {
            val response = client.get(HttpRoutes.NEW_RELEASES_ALBUMS) {
                url {
                    parameters.append("limit", NetworkConstant.PAGE_LIMIT.toString())
                    parameters.append("offset", "$offset")
                }
                headers {
                    appendAll(NetworkConstant.headers())
                }
            }
            val albumWrapper = response.body<BaseResponse<AlbumWrapperDto>>().albums!!
            val albums = albumWrapper.albumDtos.map { it.toAlbum() }
            Either.Right(albums)
        } catch (e: Exception) {
            Either.Left(e.toCustomExceptions())
        }
    }

    suspend fun getCategories(offset: Int): Either<Failure, List<Categories>> {
        return try {
            val response = client.get(HttpRoutes.CATEGORIES) {
                url {
                    parameters.append("locale", NetworkConstant.LOCALE)
                    parameters.append("limit", NetworkConstant.PAGE_LIMIT.toString())
                    parameters.append("offset", "$offset")
                }
                headers {
                    appendAll(NetworkConstant.headers())
                }
            }
            val categoriesWrapper = response.body<BaseResponse<CategoriesWrapperDto>>().albums!!
            val albums = categoriesWrapper.categoriesDto.map { it.toCategories() }
            Either.Right(albums)
        } catch (e: Exception) {
            Either.Left(e.toCustomExceptions())
        }
    }

    suspend fun getGenres(): Either<Failure, Genre> {
        return try {
            val response = client.get(HttpRoutes.GENRES) {
                headers {
                    appendAll(NetworkConstant.headers())
                }
            }
            val albumWrapper = response.body<BaseResponse<GenresDto>>().albums!!
            val albums = albumWrapper.toGenre()
            Either.Right(albums)
        } catch (e: Exception) {
            Either.Left(e.toCustomExceptions())
        }
    }
}