package com.asoom.skypulse

import android.app.Application
import com.asoom.skypulse.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SkyPulseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@SkyPulseApp)
            modules(appModule)
        }
    }
} 