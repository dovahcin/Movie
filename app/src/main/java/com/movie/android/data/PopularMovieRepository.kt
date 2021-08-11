package com.movie.android.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PopularMovieRepository(private val api: ApiServices) {


    fun getMovies(page: Int) = flow {
        emit(api.getPopularMoviesList(page.toString()))
    }.flowOn(Dispatchers.IO)

}