package com.movie.android.data

import com.movie.android.utils.DetailsDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class DetailsRepository(private val api: ApiServices) {

    fun getDataForDetailedPage(currentPage: String, movieId: String) = flow {
        val dataModel = DetailsDataModel(
            api.getSimilarMovies(currentPage, movieId),
            api.getRecommendationMovies(currentPage, movieId),
            api.getMovieDetails(movieId)
        )
        emit(dataModel)
    }.flowOn(Dispatchers.IO)

}