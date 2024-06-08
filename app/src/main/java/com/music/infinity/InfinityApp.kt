package com.music.infinity

import android.app.Application
import com.music.infinity.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class InfinityApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@InfinityApp)
            modules(appModule)
        }
    }
}