package com.music.infinity.di

import com.music.infinity.BuildConfig
import com.music.infinity.data.local.SharedPrefManager
import com.music.infinity.data.remote.NetworkConstant
import com.music.infinity.data.remote.NetworkManager
import com.music.infinity.data.remote.SpotifyApi
import com.music.infinity.data.repository.AlbumRepositoryImpl
import com.music.infinity.domain.repository.AlbumRepository
import com.music.infinity.domain.usecase.AlbumUseCase
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.timeout
import io.ktor.client.request.request
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json
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
                Json {
                    ignoreUnknownKeys = true
                }
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

    single {
        AlbumUseCase(get())
    }
}