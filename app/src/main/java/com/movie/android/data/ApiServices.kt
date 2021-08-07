package com.movie.android.data

import com.movie.android.domain.MoviesList
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiServices {


    @GET("/3/movie/popular")
    suspend fun getPopularMoviesList(@Query("page") page: String) : MoviesList

}