package com.music.infinity

import android.app.Application
import com.music.infinity.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class InfinityApp: Application() {

    companion object {
        lateinit var instance: InfinityApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(this@InfinityApp)
            modules(appModule)
        }
    }
}