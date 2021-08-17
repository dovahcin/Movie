package com.movie.android.data.network

import com.movie.android.domain.Details
import com.movie.android.domain.Movies
import com.movie.android.domain.Upcomings
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiServices {


    @GET("/3/movie/popular")
    suspend fun getPopularMoviesList(@Query("page") page: String): Movies

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: String
    ): Details

    @GET("/3/movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: String,
    ): Movies

    @GET("/3/movie/{movie_id}/recommendations")
    suspend fun getRecommendationMovies(
        @Path("movie_id") movieId: String,
    ): Movies

    @GET("3/movie/upcoming")
    suspend fun getUpcomingMovies(): Upcomings

}