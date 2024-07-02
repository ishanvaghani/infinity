package com.music.infinity.di

import com.music.infinity.BuildConfig
import com.music.infinity.data.local.SharedPrefManager
import com.music.infinity.data.remote.NetworkConstant
import com.music.infinity.data.remote.NetworkManager
import com.music.infinity.data.remote.SpotifyApi
import com.music.infinity.data.repository.AlbumRepositoryImpl
import com.music.infinity.data.repository.ArtistRepositoryImpl
import com.music.infinity.data.repository.CategoriesRepositoryImpl
import com.music.infinity.data.repository.GenreRepositoryImpl
import com.music.infinity.data.repository.PlaylistRepositoryImpl
import com.music.infinity.data.repository.RecommendationRepositoryImpl
import com.music.infinity.data.repository.SearchRepositoryImpl
import com.music.infinity.domain.repository.AlbumRepository
import com.music.infinity.domain.repository.ArtistRepository
import com.music.infinity.domain.repository.CategoriesRepository
import com.music.infinity.domain.repository.GenreRepository
import com.music.infinity.domain.repository.PlaylistRepository
import com.music.infinity.domain.repository.RecommendationRepository
import com.music.infinity.domain.repository.SearchRepository
import com.music.infinity.domain.usecase.AlbumUseCase
import com.music.infinity.domain.usecase.ArtistUseCase
import com.music.infinity.domain.usecase.CategoriesUseCase
import com.music.infinity.domain.usecase.GenreUseCase
import com.music.infinity.domain.usecase.PlaylistUseCase
import com.music.infinity.domain.usecase.RecommendationUseCase
import com.music.infinity.domain.usecase.SearchUseCase
import com.music.infinity.presentation.genres.GenresViewModel
import com.music.infinity.presentation.home.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.timeout
import io.ktor.client.request.request
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        HttpClient(OkHttp) {
            if (BuildConfig.DEBUG) {
                install(Logging) {
                    level = LogLevel.BODY
                }
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }

            engine {
                request {
                    timeout {
                        requestTimeoutMillis = 10000
                        connectTimeoutMillis = 10000
                        socketTimeoutMillis = 10000
                    }
                }
            }

            defaultRequest {
                host = NetworkConstant.BASE_URL
                url {
                    protocol = URLProtocol.HTTPS
                }
            }
        }
    }

    single {
        SharedPrefManager(get())
    }

    single {
        SpotifyApi(get())
    }

    single {
        NetworkManager(get())
    }

    // repositories
    single<AlbumRepository> {
        AlbumRepositoryImpl(get(), get())
    }

    single<SearchRepository> {
        SearchRepositoryImpl(get(), get())
    }

    single<CategoriesRepository> {
        CategoriesRepositoryImpl(get(), get())
    }

    single<GenreRepository> {
        GenreRepositoryImpl(get(), get())
    }

    single<ArtistRepository> {
        ArtistRepositoryImpl(get(), get())
    }

    single<PlaylistRepository> {
        PlaylistRepositoryImpl(get(), get())
    }

    single<RecommendationRepository> {
        RecommendationRepositoryImpl(get(), get())
    }

    // use cases
    single {
        AlbumUseCase(get())
    }

    single {
        CategoriesUseCase(get())
    }

    single {
        ArtistUseCase(get())
    }

    single {
        SearchUseCase(get())
    }

    single {
        GenreUseCase(get())
    }

    single {
        PlaylistUseCase(get())
    }

    single {
        RecommendationUseCase(get())
    }

    // viewmodels
    viewModel {
        HomeViewModel(get(), get(), get())
    }

    viewModel {
        GenresViewModel(get())
    }
}