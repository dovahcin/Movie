package com.movie.android.app

import android.app.Application
import com.movie.android.di.mainModule
import org.koin.core.context.startKoin

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin{
            modules(mainModule)
        }
    }
}