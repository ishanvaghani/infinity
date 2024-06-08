package com.music.infinity.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE

class SharedPrefManager(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences("APP_PREF", MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    fun saveString(key: String, value: String?) {
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }
}