package com.music.infinity.di

import android.util.Log
import com.music.infinity.BuildConfig
import com.music.infinity.data.local.SharedPrefManager
import com.music.infinity.data.remote.NetworkConstant
import com.music.infinity.data.remote.NetworkManager
import com.music.infinity.data.remote.SpotifyApi
import com.music.infinity.data.repository.AlbumRepositoryImpl
import com.music.infinity.data.repository.CategoriesRepositoryImpl
import com.music.infinity.data.repository.GenreRepositoryImpl
import com.music.infinity.domain.repository.AlbumRepository
import com.music.infinity.domain.repository.CategoriesRepository
import com.music.infinity.domain.repository.GenreRepository
import com.music.infinity.domain.usecase.AlbumUseCase
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.endpoint
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.timeout
import io.ktor.client.request.request
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

val appModule = module {

    single {
        HttpClient(OkHttp) {
            if (BuildConfig.DEBUG) {
                install(Logging) {
                    logger = object : Logger {
                        override fun log(message: String) {
                            Log.d("Ktor", message)
                        }
                    }
                    level = LogLevel.ALL
                }
            }

            install(ContentNegotiation) {
                json()
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

    single<AlbumRepository> {
        AlbumRepositoryImpl(get(), get())
    }

    single<CategoriesRepository> {
        CategoriesRepositoryImpl(get(), get())
    }

    single<GenreRepository> {
        GenreRepositoryImpl(get(), get())
    }

    single {
        AlbumUseCase(get())
    }
}