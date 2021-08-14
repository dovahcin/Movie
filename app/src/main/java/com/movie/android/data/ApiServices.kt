package com.movie.android.data

import com.movie.android.domain.details.Details
import com.movie.android.domain.details.recommendation.Recommendations
import com.movie.android.domain.details.similar.Similarities
import com.movie.android.domain.popular.MoviesList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiServices {


    @GET("/3/movie/popular")
    suspend fun getPopularMoviesList(@Query("page") page: String): MoviesList

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: String
    ): Details

    @GET("/3/movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: String,
    ): Similarities

    @GET("/3/movie/{movie_id}/recommendations")
    suspend fun getRecommendationMovies(
        @Path("movie_id") movieId: String,
    ): Recommendations


}