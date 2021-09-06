package com.movie.android

import android.app.Application
import com.movie.android.di.databaseModule
import com.movie.android.di.mainModule
import com.movie.android.di.networkModule
import com.movie.android.di.statesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin{
            androidContext(this@App)
            modules(listOf(networkModule, mainModule, statesModule, databaseModule))
        }
    }
}