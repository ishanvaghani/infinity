package com.music.infinity.common

import android.util.Base64
import com.music.infinity.data.remote.model.Failure
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.utils.io.errors.IOException

fun String.encodeBase64(): String {
    return Base64.encodeToString(toByteArray(charset("UTF-8")), Base64.NO_WRAP)
}

fun String?.isNotNullOrEmpty(): Boolean {
    return isNullOrEmpty().not()
}

fun Exception.toCustomExceptions() = when (this) {
    is RedirectResponseException -> { // 3xx responses
        Failure.HttpError(this.message)
    }

    is ServerResponseException -> { // 5xx responses
        Failure.HttpError(this.message)
    }

    is ResponseException -> { // generic response exception
        Failure.HttpError(this.message)
    }

    is IOException -> { // network I/O errors
        Failure.HttpError(this.message)
    }

    else -> Failure.GenericError(this.message)
}

fun HttpStatusCode.toCustomExceptions() = when (this.value) {
    401 -> {
        Failure.HttpErrorUnauthorized("Unauthorized")
    }

    else -> {
        Failure.HttpError("Http error")
    }
}

fun HttpResponse.isSuccess(): Boolean {
    return status.value == 200
}