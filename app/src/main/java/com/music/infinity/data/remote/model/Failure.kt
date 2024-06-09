package com.music.infinity.data.remote.model

sealed class Failure {
    class HttpErrorUnauthorized(val message: String) : Failure()
    class HttpError(val message: String?) : Failure()
    class GenericError(val message: String?) : Failure()
}