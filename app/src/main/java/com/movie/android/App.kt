package com.movie.android

import android.app.Application
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.installations.InstallationTokenResult
import com.movie.android.di.databaseModule
import com.movie.android.di.mainModule
import com.movie.android.di.networkModule
import com.movie.android.di.statesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import androidx.annotation.NonNull

import com.google.firebase.messaging.FirebaseMessaging




class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
        FirebaseMessaging.getInstance().token.addOnSuccessListener { token ->
            Log.i("Firebase Token", token)
        }
    }

    private fun startKoin() {
        startKoin{
            androidContext(this@App)
            modules(listOf(networkModule, mainModule, statesModule, databaseModule))
        }
    }
}