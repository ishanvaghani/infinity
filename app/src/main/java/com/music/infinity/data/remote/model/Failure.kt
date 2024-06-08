package com.music.infinity.data.remote.model

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException

sealed class Failure {
    class HttpErrorInternalServerError(val exception: ServerResponseException) : Failure()
    class HttpErrorBadRequest(val exception: ClientRequestException) : Failure()
    class HttpErrorUnauthorized(val exception: ClientRequestException) : Failure()
    class HttpErrorForbidden(val exception: ClientRequestException) : Failure()
    class HttpErrorNotFound(val exception: ClientRequestException) : Failure()
    class HttpError(val exception: Exception) : Failure()
    class GenericError(val exception: Exception) : Failure()
}