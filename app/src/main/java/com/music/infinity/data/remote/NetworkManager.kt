package com.music.infinity.data.remote

import arrow.core.Either
import com.music.infinity.common.isNotNullOrEmpty
import com.music.infinity.data.local.SharedPrefs
import com.music.infinity.data.remote.model.Failure
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class NetworkManager(private val spotifyApi: SpotifyApi) {

    private val mutex = Mutex()
    private var refreshTokenDeferred: CompletableDeferred<Unit>? = null

    suspend fun <T> executeWithAuthentication(
        block: suspend () -> Either<Failure, T>
    ): Either<Failure, T> {
        val response = block()
        if (response.leftOrNull() is Failure.HttpErrorUnauthorized) {
            mutex.withLock {
                if (refreshTokenDeferred != null) {
                    // wait for auth token
                    refreshTokenDeferred?.await()
                } else {
                    // get auth token
                    refreshTokenDeferred = CompletableDeferred()
                    val token = spotifyApi.getAccessToken().getOrNull()
                    if (token.isNotNullOrEmpty()) {
                        SharedPrefs.setAccessToken(token)
                    }
                    refreshTokenDeferred?.complete(Unit)
                    refreshTokenDeferred = null
                }
            }
            return block()
        }
        return response
    }
}