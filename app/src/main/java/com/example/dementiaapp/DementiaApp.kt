package com.example.dementiaapp

import android.app.Application
import com.example.dementiaapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DementiaApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@DementiaApp)
            modules(appModule)
        }
    }
}