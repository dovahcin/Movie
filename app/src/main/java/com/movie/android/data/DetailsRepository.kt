package com.movie.android.data

import android.util.Log
import com.movie.android.utils.DetailsDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class DetailsRepository(private val api: ApiServices) {

    fun getDataForDetailedPage(movieId: Int) = flow {
        val similars = api.getSimilarMovies(movieId.toString())
        val genres = api.getMovieDetails(movieId.toString())
        val recommendations = api.getRecommendationMovies( movieId.toString())
        Log.d("inrepository", "dataModel : $similars $genres $recommendations")
        val dataModel = DetailsDataModel(
            similars,
            genres,
            recommendations
        )
        emit(dataModel)
    }.flowOn(Dispatchers.IO)

}