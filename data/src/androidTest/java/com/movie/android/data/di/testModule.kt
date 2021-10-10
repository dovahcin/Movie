package com.movie.android.data.di

import androidx.room.Room
import com.movie.android.data.BuildConfig
import com.movie.android.data.db.MovieDatabase
import com.movie.android.data.network.ApiInterceptor
import com.movie.android.data.network.ApiServices
import com.movie.android.data.utils.TestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val testModule = module {
        single {
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.API_URL)
                .client(
                    OkHttpClient.Builder()
                        .readTimeout(15.toLong(), TimeUnit.SECONDS)
                        .connectTimeout(15.toLong(), TimeUnit.SECONDS)
                        .addInterceptor(ApiInterceptor())
                        .addInterceptor(TestInterceptor())
                        .addInterceptor(
                            HttpLoggingInterceptor().setLevel(
                                if (BuildConfig.DEBUG)
                                    HttpLoggingInterceptor.Level.BODY
                                else HttpLoggingInterceptor.Level.NONE
                            )
                        )
                        .build()
                )
                .build().create(ApiServices::class.java)
        }

    single {
        Room.inMemoryDatabaseBuilder(
            androidContext(),
            MovieDatabase::class.java)
            .allowMainThreadQueries().build()
    }
}