package com.music.infinity.data.local

import com.music.infinity.common.AppConstants
import com.music.infinity.common.isNotNullOrEmpty
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object SharedPrefs : KoinComponent {

    private val sharedPrefManager: SharedPrefManager by inject()

    fun getAccessToken(): String? {
        if (AppConstants.accessToken.isNotNullOrEmpty()) {
                return AppConstants.accessToken
        }
        AppConstants.accessToken = sharedPrefManager.getString(SharedPrefKeys.ACCESS_TOKEN)
        return AppConstants.accessToken
    }

    fun setAccessToken(accessToken: String?) {
        AppConstants.accessToken = accessToken
        sharedPrefManager.saveString(SharedPrefKeys.ACCESS_TOKEN, accessToken)
    }
}