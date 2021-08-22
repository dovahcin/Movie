package com.movie.android.di

import com.movie.android.BuildConfig
import com.movie.android.popular.PopularRepository
import com.movie.android.details.DetailsRepository
import com.movie.android.data.network.ApiInterceptor
import com.movie.android.data.network.ApiServices
import com.movie.android.details.DetailsViewModel
import com.movie.android.explore.ExploreRepository
import com.movie.android.explore.ExploreViewModel
import com.movie.android.popular.PopularViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    if (BuildConfig.DEBUG)
                        HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
                ))
            .build())
        .build().create(ApiServices::class.java) }

}

val mainModule = module {

    viewModel{ PopularViewModel(get()) }
    single { PopularRepository(get()) }
    viewModel { DetailsViewModel(get()) }
    single { DetailsRepository(get()) }
    viewModel { ExploreViewModel(get()) }
    single { ExploreRepository(get()) }

}
