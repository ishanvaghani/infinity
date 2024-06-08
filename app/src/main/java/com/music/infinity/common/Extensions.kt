package com.music.infinity.common

import android.util.Base64
import com.music.infinity.data.remote.model.Failure
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException

fun String.encodeBase64(): String {
    return Base64.encodeToString(toByteArray(charset("UTF-8")), Base64.NO_WRAP)
}

fun String?.isNotNullOrEmpty(): Boolean {
    return isNullOrEmpty().not()
}

fun Exception.toCustomExceptions() = when (this) {
    is ServerResponseException -> Failure.HttpErrorInternalServerError(this)
    is ClientRequestException ->
        when (response.status.value) {
            400 -> Failure.HttpErrorBadRequest(this)
            401 -> Failure.HttpErrorUnauthorized(this)
            403 -> Failure.HttpErrorForbidden(this)
            404 -> Failure.HttpErrorNotFound(this)
            else -> Failure.HttpError(this)
        }

    is RedirectResponseException -> Failure.HttpError(this)
    else -> Failure.GenericError(this)
}

fun Failure.shouldRetry(): Boolean {
    return this is Failure.HttpErrorInternalServerError ||
            this is Failure.HttpErrorBadRequest ||
            this is Failure.HttpErrorForbidden ||
            this is Failure.HttpError ||
            this is Failure.GenericError
}