package com.music.infinity.data.remote.model

sealed class Failure : Throwable() {
    class HttpErrorUnauthorized(override val message: String) : Failure()
    class HttpError(override val message: String?) : Failure()
    class GenericError(override val message: String?) : Failure()
}