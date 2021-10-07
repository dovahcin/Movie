package com.movie.android.data

import com.movie.android.data.MovieListRepository.Companion.DEFAULT_PAGE
import com.movie.android.data.network.ApiServices
import com.movie.android.domain.DetailsDataModel
import com.movie.android.domain.ExploreItem.Type.RecommendedMovieList
import com.movie.android.domain.ExploreItem.Type.SimilarMovieList
import com.movie.android.domain.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieDetailsRepository(private val api: ApiServices) {

    fun getDataForDetailedPage(movieId: Int) = flow {

        val similars = api.getSimilarMovies(movieId.toString(), DEFAULT_PAGE.toString())
            .also { it.id = SimilarMovieList.ordinal }
        val recommendations =
            api.getRecommendationMovies(movieId.toString(), DEFAULT_PAGE.toString())
                .also { it.id = RecommendedMovieList.ordinal }

        val details = api.getMovieDetails(movieId.toString())

        when {
            similars.results.size == 0 -> similars.results = mutableListOf()
            similars.results.size >= 10 -> {
                similars.results = (similars.results
                    .take(10) + Movie.createShowMore()).toMutableList()
            }
            else -> similars.results = similars.results
        }
        when {
            recommendations.results.size == 0 -> recommendations.results = mutableListOf()
            recommendations.results.size >= 10 -> {
                recommendations
                    .results = (recommendations.results
                    .take(10) + Movie.createShowMore()).toMutableList()
            }
            else -> recommendations.results = recommendations.results
        }
        emit(DetailsDataModel(details, similars, recommendations))
    }.flowOn(Dispatchers.IO)

}