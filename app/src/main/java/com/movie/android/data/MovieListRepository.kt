package com.movie.android.data

import android.util.Log
import com.movie.android.data.network.ApiServices
import com.movie.android.domain.ExploreItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieListRepository(private val api: ApiServices) {

    fun getMovies(listId: Int, page: Int) = flow {

        when (listId) {
            ExploreItem.Type.HorizontalMovieList.ordinal -> {
                emit(api.getUpcomingMovies(page.toString()))
                Log.d("VerticalsRepository", "Popular called : ${api.getUpcomingMovies(page.toString())}")
            }
            ExploreItem.Type.VerticalMovieList.ordinal -> {
                emit(api.getPopularMoviesList(page.toString()))
                Log.d("VerticalsRepository", "Upcoming called : ${api.getPopularMoviesList(page.toString())}")
            }
        }

    }.flowOn(Dispatchers.IO)

}