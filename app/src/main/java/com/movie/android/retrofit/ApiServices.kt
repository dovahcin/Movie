package com.movie.android.retrofit

import com.movie.android.datamodel.MoviesList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiServices {

    @GET("/3/movie/popular")
    suspend fun getPopularMoviesList(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: String
    ) : Response<MoviesList>

}