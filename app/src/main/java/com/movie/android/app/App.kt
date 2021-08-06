package com.movie.android.app

import android.app.Application
import com.movie.android.di.viewModule
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin{
            modules(viewModule)
        }
    }
}