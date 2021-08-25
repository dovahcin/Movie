package com.movie.android.data

import com.movie.android.data.network.ApiServices
import com.movie.android.domain.Movie
import com.movie.android.domain.DetailsDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DetailsRepository(private val api: ApiServices) {

  companion object {
    const val SIMILARS = 2
    const val RECOMMENDS = 3
  }

    fun getDataForDetailedPage(movieId: Int) = flow {
      val similars = api.getSimilarMovies(movieId.toString()).also { it.id = SIMILARS }
      val recommendations = api.getRecommendationMovies( movieId.toString()) .also { it.id = RECOMMENDS }
      val details = api.getMovieDetails(movieId.toString())

      similars.results = (similars.results
        .take(10) + Movie.createShowMore()).toMutableList()
      recommendations.results = (recommendations.results
        .take(10) + Movie.createShowMore()).toMutableList()

        emit(DetailsDataModel(details, similars, recommendations))
    }.flowOn(Dispatchers.IO)

}