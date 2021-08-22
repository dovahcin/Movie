package com.movie.android.data

import com.movie.android.data.network.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieListRepository(private val api: ApiServices) {

    fun getMovies(listId: Int, page: Int) = flow {
        //TODO with a when we should return the respective list.
        emit(api.getPopularMoviesList(page.toString()))
    }.flowOn(Dispatchers.IO)

}