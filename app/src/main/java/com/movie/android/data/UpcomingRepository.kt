package com.movie.android.data

import com.movie.android.data.network.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UpcomingRepository(private val api: ApiServices) {

    fun getMovies(page: Int) = flow {
        emit(api.getUpcomingMovies(page.toString()))
    }.flowOn(Dispatchers.IO)

}