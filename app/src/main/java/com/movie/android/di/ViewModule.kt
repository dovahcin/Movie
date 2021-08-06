package com.movie.android.di

import com.movie.android.picasso.PicassoService
import com.movie.android.recyclerviews.RecyclerPopularMoviesAdapter
import com.movie.android.repository.MainRepository
import com.movie.android.retrofit.RetrofitServiceForPopularList
import com.movie.android.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModule = module {

    viewModel{ MainViewModel(get()) }
    single { MainRepository(get()) }
    single { RetrofitServiceForPopularList }
    single { RecyclerPopularMoviesAdapter(listOf(), get()) }
    single { PicassoService }

}
