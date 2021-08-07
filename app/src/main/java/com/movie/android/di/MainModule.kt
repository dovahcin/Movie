package com.movie.android.di

import com.movie.android.view.recyclerviews.RecyclerPopularAdapter
import com.movie.android.repository.MainRepository
import com.movie.android.retrofit.ApiClient
import com.movie.android.retrofit.ApiServices
import com.movie.android.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val mainModule = module {

    viewModel{ MainViewModel(get()) }
    single { MainRepository(get()) }
    single { RecyclerPopularAdapter(listOf()) }
    single { ApiClient }

}
