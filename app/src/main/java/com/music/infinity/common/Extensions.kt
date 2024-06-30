package com.music.infinity.common

import android.util.Base64
import com.music.infinity.data.remote.model.Failure
import com.music.infinity.domain.model.Artist
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

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

fun CoroutineScope.launchIO(
    coroutineExceptionHandler: CoroutineExceptionHandler,
    block: suspend (CoroutineScope) -> Unit
) = launch(Dispatchers.IO + coroutineExceptionHandler) {
    block(this)
}

fun CoroutineScope.launchIO(block: suspend (CoroutineScope) -> Unit) = launch(Dispatchers.IO) {
    block(this)
}

fun List<Artist>.toArtistsString(): String {
    return joinToString(", ") { it.name }
}

fun String.formatDate(): String? {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd")
    val date = inputFormat.parse(this)
    val outputFormat = SimpleDateFormat("dd, MMM yyyy")
    return date?.let { outputFormat.format(it) }
}