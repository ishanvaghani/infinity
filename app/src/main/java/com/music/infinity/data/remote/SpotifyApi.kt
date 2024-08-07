package com.music.infinity.data.remote

import arrow.core.Either
import com.music.infinity.BuildConfig
import com.music.infinity.common.encodeBase64
import com.music.infinity.common.getCountryCode
import com.music.infinity.common.isSuccess
import com.music.infinity.common.toCustomExceptions
import com.music.infinity.data.remote.dto.AlbumDetailDto
import com.music.infinity.data.remote.dto.AlbumListDto
import com.music.infinity.data.remote.dto.ArtistAlbumWrapperDto
import com.music.infinity.data.remote.dto.ArtistInfoDto
import com.music.infinity.data.remote.dto.AuthTokenDto
import com.music.infinity.data.remote.dto.CategoriesListDto
import com.music.infinity.data.remote.dto.CategoryDto
import com.music.infinity.data.remote.dto.GenresDto
import com.music.infinity.data.remote.dto.PlaylistDto
import com.music.infinity.data.remote.dto.PlaylistListDto
import com.music.infinity.data.remote.dto.PlaylistWrapper
import com.music.infinity.data.remote.dto.RecommendationListDto
import com.music.infinity.data.remote.dto.RelatedArtistDto
import com.music.infinity.data.remote.dto.ResponseWrapper
import com.music.infinity.data.remote.dto.SearchListDto
import com.music.infinity.data.remote.dto.TrackListDto
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
                val albumWrapper = response.body<ResponseWrapper<AlbumListDto>>().albums
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

    suspend fun search(query: String, type: String, offset: Int): Either<Failure, SearchListDto> {
        return try {
            val response = client.get(HttpRoutes.SEARCH) {
                url {
                    parameters.append("q", query)
                    parameters.append("type", type)
                    parameters.append("limit", NetworkConstant.PAGE_LIMIT.toString())
                    parameters.append("offset", "$offset")
                }
                headers {
                    appendAll(NetworkConstant.headers())
                }
            }
            if (response.isSuccess()) {
                val trackList = response.body<SearchListDto>()
                Either.Right(trackList)
            } else {
                Either.Left(response.status.toCustomExceptions())
            }
        } catch (e: Exception) {
            Either.Left(e.toCustomExceptions())
        }
    }

    suspend fun getCategories(offset: Int): Either<Failure, CategoriesListDto> {
        return try {
            val response = client.get(HttpRoutes.CATEGORIES) {
                url {
                    parameters.append("locale", getCountryCode())
                    parameters.append("limit", NetworkConstant.PAGE_LIMIT.toString())
                    parameters.append("offset", "$offset")
                }
                headers {
                    appendAll(NetworkConstant.headers())
                }
            }
            if (response.isSuccess()) {
                val categoriesList = response.body<CategoriesListDto>()
                Either.Right(categoriesList)
            } else {
                Either.Left(response.status.toCustomExceptions())
            }
        } catch (e: Exception) {
            Either.Left(e.toCustomExceptions())
        }
    }

    suspend fun getCategory(id: String): Either<Failure, CategoryDto> {
        return try {
            val response = client.get(HttpRoutes.CATEGORIES) {
                url {
                    path(id)
                }
                headers {
                    appendAll(NetworkConstant.headers())
                }
            }
            if (response.isSuccess()) {
                val categoriesList = response.body<CategoryDto>()
                Either.Right(categoriesList)
            } else {
                Either.Left(response.status.toCustomExceptions())
            }
        } catch (e: Exception) {
            Either.Left(e.toCustomExceptions())
        }
    }

    suspend fun getGenres(): Either<Failure, GenresDto> {
        return try {
            val response = client.get(HttpRoutes.GENRES) {
                headers {
                    appendAll(NetworkConstant.headers())
                }
            }
            if (response.isSuccess()) {
                val genres = response.body<GenresDto>()
                Either.Right(genres)
            } else {
                Either.Left(response.status.toCustomExceptions())
            }
        } catch (e: Exception) {
            Either.Left(e.toCustomExceptions())
        }
    }

    suspend fun getArtistInfo(id: String): Either<Failure, ArtistInfoDto> {
        return try {
            val response = client.get(HttpRoutes.ARTIST_INFO + "/" + id) {
//                url {
//                    path(id)
//                }
                headers {
                    appendAll(NetworkConstant.headers())
                }
            }
            if (response.isSuccess()) {
                val artistInfo = response.body<ArtistInfoDto>()
                Either.Right(artistInfo)
            } else {
                Either.Left(response.status.toCustomExceptions())
            }

        } catch (e: Exception) {
            Either.Left(e.toCustomExceptions())
        }
    }


    suspend fun getArtistAlbums(
        id: String,
    ): Either<Failure, ArtistAlbumWrapperDto> {
        return try {
            val response = client.get(HttpRoutes.ARTIST_INFO + "/$id/${HttpRoutes.ALBUMS}") {
                url {
//                    path(id)
//                    path(HttpRoutes.ALBUMS)
                    parameters.append("market", "ES" )
                    parameters.append("limit", "10")
                    parameters.append("offset", "0")
                }
                headers {
                    appendAll(NetworkConstant.headers())
                }
            }
            if (response.isSuccess()) {
                val artistAlbum = response.body<ArtistAlbumWrapperDto>()
                Either.Right(artistAlbum)
            } else {
                Either.Left(response.status.toCustomExceptions())
            }

        } catch (e: Exception) {
            Either.Left(e.toCustomExceptions())
        }
    }

    suspend fun getRelatedArtists(id: String): Either<Failure, RelatedArtistDto> {
        return try {
            val response = client.get(HttpRoutes.ARTIST_INFO+ "/"+ id +"/" + HttpRoutes.RELATED_ARTIST) {
                url {
//                    parameters.append("id", id)
                }
                headers {
                    appendAll(NetworkConstant.headers())
                }
            }

            if (response.isSuccess()) {
                val relatedArtist = response.body<RelatedArtistDto>()
                Either.Right(relatedArtist)
            } else {
                Either.Left(response.status.toCustomExceptions())
            }
        } catch (e: Exception) {
            Either.Left(e.toCustomExceptions())
        }
    }

    suspend fun getFeaturedPlaylists(): Either<Failure, PlaylistListDto> {
        return try {
            val response = client.get(HttpRoutes.FEATURED_PLAYLISTS) {
                url {
                    parameters.append("locale", getCountryCode())
                    parameters.append("limit", NetworkConstant.PAGE_LIMIT.toString())
                    parameters.append("offset", 0.toString())
                }
                headers {
                    appendAll(NetworkConstant.headers())
                }
            }
            if (response.isSuccess()) {
                val playlistList = response.body<PlaylistWrapper>().playlists
                Either.Right(playlistList)
            } else {
                Either.Left(response.status.toCustomExceptions())
            }
        } catch (e: Exception) {
            Either.Left(e.toCustomExceptions())
        }
    }

    suspend fun getPlaylist(id: String): Either<Failure, PlaylistDto> {
        return try {
            val response = client.get(HttpRoutes.PLAYLISTS) {
                url {
                    path(id)
                }
                headers {
                    appendAll(NetworkConstant.headers())
                }
            }
            if (response.isSuccess()) {
                val playlist = response.body<PlaylistDto>()
                Either.Right(playlist)
            } else {
                Either.Left(response.status.toCustomExceptions())
            }
        } catch (e: Exception) {
            Either.Left(e.toCustomExceptions())
        }
    }

    suspend fun getRecommendations(genres: String? = null): Either<Failure, RecommendationListDto> {
        return try {
            val response = client.get(HttpRoutes.RECOMMENDATIONS) {
                url {
                    parameters.append("locale", getCountryCode())
                    parameters.append("limit", NetworkConstant.PAGE_LIMIT.toString())
                    parameters.append("offset", 0.toString())
                    if (genres != null) {
                        parameters.append("seed_genres", genres)
                    }
                }
                headers {
                    appendAll(NetworkConstant.headers())
                }
            }
            if (response.isSuccess()) {
                val playlistList = response.body<RecommendationListDto>()
                Either.Right(playlistList)
            } else {
                Either.Left(response.status.toCustomExceptions())
            }
        } catch (e: Exception) {
            Either.Left(e.toCustomExceptions())
        }
    }
}