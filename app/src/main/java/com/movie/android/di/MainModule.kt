package com.movie.android.di

import com.movie.android.BuildConfig
import com.movie.android.data.ApiInterceptor
import com.movie.android.view.adapter.PopularMovieAdapter
import com.movie.android.data.MainRepository
import com.movie.android.data.ApiServices
import com.movie.android.view.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single { Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.API_URL)
        .client( OkHttpClient.Builder()
            .readTimeout(15.toLong(), TimeUnit.SECONDS)
            .connectTimeout(15.toLong(), TimeUnit.SECONDS)
            .addInterceptor(ApiInterceptor())
            .build())
        .build().create(ApiServices::class.java) }

}

val mainModule = module {

    viewModel{ MainViewModel(get()) }
    single { MainRepository(get()) }

}

val  viewModule = module {

    single { PopularMovieAdapter(listOf()) }
}
