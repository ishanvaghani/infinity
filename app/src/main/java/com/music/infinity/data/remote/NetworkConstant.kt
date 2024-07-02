package com.music.infinity.data.remote

import com.music.infinity.data.local.SharedPrefs
import io.ktor.http.HttpHeaders
import io.ktor.util.StringValues

object NetworkConstant {
    const val AUTH_BASE_URL = "accounts.spotify.com"
    const val BASE_URL = "api.spotify.com/v1"

    const val PAGE_LIMIT = 10
    const val MAX_RETRIES = 3

    fun headers(): StringValues {
        return StringValues.build {
            append(HttpHeaders.Authorization, "Bearer ${SharedPrefs.getAccessToken()}")
        }
    }
}