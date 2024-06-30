package com.music.infinity.common

import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import com.music.infinity.InfinityApp
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun getCountryCode(): String {
    val defaultCountry = "IN"
    try {
        val tm =
            InfinityApp.instance.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        // country based on sim
        val simCountry = tm.simCountryIso
        if (simCountry != null && simCountry.length == 2) {
            return simCountry.uppercase()
        }
        // country based on network
        val networkCountry = tm.networkCountryIso
        if (networkCountry != null && networkCountry.length == 2) {
            return networkCountry.uppercase()
        }
        // local country
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            InfinityApp.instance.resources.configuration.locales[0].country
        } else {
            defaultCountry
        }
    } catch (e: Exception) {
        return defaultCountry
    }
}