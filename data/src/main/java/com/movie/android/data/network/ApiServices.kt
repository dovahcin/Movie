package com.movie.android.data.network

import com.movie.android.domain.ActorDetails
import com.movie.android.domain.ActorList
import com.movie.android.domain.Details
import com.movie.android.domain.MovieList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiServices {


    @GET("/3/movie/popular")
    suspend fun getPopularMoviesList(
        @Query("page") page: String
    ): MovieList

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: String
    ): Details

    @GET("/3/movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: String,
        @Query("page") page: String
    ): MovieList

    @GET("/3/movie/{movie_id}/recommendations")
    suspend fun getRecommendationMovies(
        @Path("movie_id") movieId: String,
        @Query("page") page: String
    ): MovieList

    @GET("/3/movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: String
    ): MovieList

    @GET("/3/person/{person_id}")
    suspend fun getActorDetails(
        @Path("person_id") personId: String
    ): ActorDetails

    @GET("/3/person/popular")
    suspend fun getArtists(
        @Query("page") page: String
    ): ActorList

    @GET("/3/discover/movie")
    suspend fun getActorMovies(
        @Query("with_people") personId: String,
        @Query("page") page: String
    ): MovieList

    @GET("/3/search/movie")
    suspend fun getQueryResults(
        @Query("query") query: String
    ): MovieList

}