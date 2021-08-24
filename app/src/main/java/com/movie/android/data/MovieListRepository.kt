package com.movie.android.data

import com.movie.android.data.network.ApiServices
import com.movie.android.domain.ExploreItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieListRepository(private val api: ApiServices) {



    fun getMovies(listId: Int, page: Int, movieId: Int) = flow {

        when (listId) {
            ExploreItem.Type.HorizontalMovieList.ordinal -> emit(api.getUpcomingMovies(page.toString()))
            ExploreItem.Type.VerticalMovieList.ordinal -> emit(api.getPopularMoviesList(page.toString()))
            2 -> emit(api.getSimilarMovies(movieId.toString()))
            3 -> emit(api.getRecommendationMovies(movieId.toString()))
        }

    }.flowOn(Dispatchers.IO)

}