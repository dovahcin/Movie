package com.movie.android.data

import com.movie.android.domain.details.Details
import com.movie.android.domain.details.Recommendations
import com.movie.android.domain.details.Similarities
import com.movie.android.domain.popular.MoviesList
import com.movie.android.utils.DetailsDataModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiServices {


    @GET("/3/movie/popular")
    suspend fun getPopularMoviesList(@Query("page") page: String): MoviesList

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: String
    ): Details

    @GET("/movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Query("page") page: String,
        @Path("movie_id") movieId: String
    ): Similarities

    @GET("/movie/{movie_id}/recommendations")
    suspend fun getRecommendationMovies(
        @Query("page") page: String,
        @Path("movie_id") movieId: String
    ): Recommendations


}