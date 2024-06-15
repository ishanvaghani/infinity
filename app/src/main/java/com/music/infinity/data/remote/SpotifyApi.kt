package com.music.infinity.data.remote

import arrow.core.Either
import com.music.infinity.BuildConfig
import com.music.infinity.common.encodeBase64
import com.music.infinity.common.isSuccess
import com.music.infinity.common.toCustomExceptions
import com.music.infinity.data.remote.dto.AlbumDetailDto
import com.music.infinity.data.remote.dto.AlbumListDto
import com.music.infinity.data.remote.dto.AuthTokenDto
import com.music.infinity.data.remote.dto.TrackListDto
import com.music.infinity.data.remote.model.BaseResponse
import com.music.infinity.data.remote.model.Failure
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.path

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
                    append(HttpHeaders.Authorization, "Basic $clientToken")
                    append(HttpHeaders.ContentType, "application/x-www-form-urlencoded")
                }
                setBody("grant_type=client_credentials")
            }
            return Either.Right(response.body<AuthTokenDto>().accessToken)
        } catch (e: Exception) {
            return Either.Left(e.toCustomExceptions())
        }
    }

    suspend fun getNewReleasesAlbums(offset: Int): Either<Failure, AlbumListDto> {
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
            if (response.isSuccess()) {
                val albumWrapper = response.body<BaseResponse<AlbumListDto>>().albums!!
                Either.Right(albumWrapper)
            } else {
                Either.Left(response.status.toCustomExceptions())
            }
        } catch (e: Exception) {
            Either.Left(e.toCustomExceptions())
        }
    }

    suspend fun getAlbum(id: String): Either<Failure, AlbumDetailDto> {
        return try {
            val response = client.get(HttpRoutes.ALBUMS) {
                url {
                    path(id)
                }
                headers {
                    appendAll(NetworkConstant.headers())
                }
            }
            if (response.isSuccess()) {
                val albumDetail = response.body<AlbumDetailDto>()
                Either.Right(albumDetail)
            } else {
                Either.Left(response.status.toCustomExceptions())
            }
        } catch (e: Exception) {
            Either.Left(e.toCustomExceptions())
        }
    }

    suspend fun getAlbumTracks(id: String, offset: Int): Either<Failure, TrackListDto> {
        return try {
            val response = client.get(HttpRoutes.ALBUMS) {
                url {
                    path(id, "tracks")
                    parameters.append("limit", NetworkConstant.PAGE_LIMIT.toString())
                    parameters.append("offset", "$offset")
                }
                headers {
                    appendAll(NetworkConstant.headers())
                }
            }
            if (response.isSuccess()) {
                val trackList = response.body<TrackListDto>()
                Either.Right(trackList)
            } else {
                Either.Left(response.status.toCustomExceptions())
            }
        } catch (e: Exception) {
            Either.Left(e.toCustomExceptions())
        }
    }
}