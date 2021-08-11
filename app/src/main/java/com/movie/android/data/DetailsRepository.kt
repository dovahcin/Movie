package com.movie.android.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class DetailsRepository(private val api: ApiServices) {

    fun getDataForDetailedPage(movieId: String) = flow {
        emit(api.getMovieDetails(movieId))
    }.flowOn(Dispatchers.IO)

    fun getSimilars(currentPage: String, movieId: String) = flow {
        emit(api.getSimilarMovies(currentPage, movieId))
    }.flowOn(Dispatchers.IO)

    fun getRecommendations(currentPage: String, movieId: String) = flow {
        emit(api.getRecommendationMovies(currentPage, movieId))
    }.flowOn(Dispatchers.IO)

}