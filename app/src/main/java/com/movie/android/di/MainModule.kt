package com.movie.android.di

import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import com.movie.android.BuildConfig
import com.movie.android.actordetails.ActorDetailsViewModel
import com.movie.android.actorlist.ActorListViewModel
import com.movie.android.data.db.MovieDatabase
import com.movie.android.data.network.ApiInterceptor
import com.movie.android.data.network.ApiServices
import com.movie.android.explore.ExploreViewModel
import com.movie.android.data.ActorDetailsRepository
import com.movie.android.data.ActorListRepository
import com.movie.android.data.ExploreRepository
import com.movie.android.data.MovieDetailsRepository
import com.movie.android.data.MovieListRepository
import com.movie.android.data.SearchRepository
import com.movie.android.moviedetails.MovieDetailsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.API_URL)
            .client(
                OkHttpClient.Builder()
                    .readTimeout(15.toLong(), TimeUnit.SECONDS)
                    .connectTimeout(15.toLong(), TimeUnit.SECONDS)
                    .addInterceptor(ApiInterceptor())
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

}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            "movie_db"
        ).build().searchHistory()
    }
}

val statesModule = module {
    single { SavedStateHandle() }
}

val mainModule = module {

    viewModel { com.movie.android.movielist.MovieListViewModel(get()) }
    single { MovieListRepository(get()) }
    viewModel { MovieDetailsViewModel(get()) }
    single { MovieDetailsRepository(get()) }
    viewModel { ExploreViewModel(get(), get()) }
    single { ExploreRepository(get()) }
    viewModel { ActorDetailsViewModel(get(), get()) }
    single { ActorDetailsRepository(get()) }
    viewModel { com.movie.android.search.SearchViewModel(get(), get()) }
    single { SearchRepository(get(), get()) }
    single { ActorListRepository(get()) }
    viewModel { ActorListViewModel(get()) }
}
