package com.movie.android.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainRepository(private val api: ApiServices) {

    private val page = "1"

    fun getPopularMovies() = flow {
        emit(api.getPopularMoviesList(page))
    }.flowOn(Dispatchers.IO)

}