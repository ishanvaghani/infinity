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

    fun getSelectedGenres(): String? {
        return sharedPrefManager.getString(SharedPrefKeys.SELECTED_GENRES)
    }

    fun setSelectedGenres(genres: String) {
        sharedPrefManager.saveString(SharedPrefKeys.SELECTED_GENRES, genres)
    }

    fun addTrackToFavourites(trackId : String){
        var oldFavData = sharedPrefManager.getString(SharedPrefKeys.FAVOURITES_TRACKS) ?: ""
        oldFavData = "$oldFavData$trackId,"
        sharedPrefManager.saveString(SharedPrefKeys.FAVOURITES_TRACKS, oldFavData)
    }

    fun removeTrackFromFavourites(trackId: String){
        var oldFavData = sharedPrefManager.getString(SharedPrefKeys.FAVOURITES_TRACKS) ?: ""
        val startIndex = oldFavData.indexOf(trackId)
        oldFavData = oldFavData.removeRange(startIndex, startIndex + trackId.length + 2)
        sharedPrefManager.saveString(SharedPrefKeys.FAVOURITES_TRACKS, oldFavData)
    }

    fun getFavouritesAllData() : ArrayList<String>{
        val lstFavouritesData : ArrayList<String> = arrayListOf()
        val favData = sharedPrefManager.getString(SharedPrefKeys.FAVOURITES_TRACKS) ?: ""
        lstFavouritesData.addAll(favData.split(","))
        if(lstFavouritesData.isNotEmpty()){
            lstFavouritesData.removeLast()
        }
        return lstFavouritesData
    }

}