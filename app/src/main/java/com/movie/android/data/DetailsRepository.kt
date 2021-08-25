package com.movie.android.data

import com.movie.android.data.network.ApiServices
import com.movie.android.domain.DetailsDataModel
import com.movie.android.domain.ExploreItem.Type.RecommendedMovieList
import com.movie.android.domain.ExploreItem.Type.SimilarMovieList
import com.movie.android.domain.Movie
import com.movie.android.presentation.features.movielist.MovieListFragment.Companion.DEFAULT_PAGE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DetailsRepository(private val api: ApiServices) {

    fun getDataForDetailedPage(movieId: Int) = flow {

      val similars = api.getSimilarMovies(movieId.toString(), DEFAULT_PAGE.toString())
        .also { it.id = SimilarMovieList.ordinal }
      val recommendations = api.getRecommendationMovies( movieId.toString(), DEFAULT_PAGE.toString())
        .also { it.id = RecommendedMovieList.ordinal }

      val details = api.getMovieDetails(movieId.toString())

      similars.results = (similars.results
        .take(10) + Movie.createShowMore()).toMutableList()
      recommendations.results = (recommendations.results
        .take(10) + Movie.createShowMore()).toMutableList()

        emit(DetailsDataModel(details, similars, recommendations))
    }.flowOn(Dispatchers.IO)

}