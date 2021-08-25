package com.movie.android.data

import com.movie.android.data.network.ApiServices
import com.movie.android.domain.ExploreItem.Type.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieListRepository(private val api: ApiServices) {

    fun getMovies(listId: Int, page: Int, movieId: Int) = flow {

        when (listId) {
            HorizontalMovieList.ordinal -> emit(api.getUpcomingMovies(page.toString()))
            VerticalMovieList.ordinal -> emit(api.getPopularMoviesList(page.toString()))
            SimilarMovieList.ordinal -> emit(api.getSimilarMovies(movieId.toString(), page.toString()))
            RecommendedMovieList.ordinal -> emit(api.getRecommendationMovies(movieId.toString(), page.toString()))
        }

    }.flowOn(Dispatchers.IO)

}