package com.movie.android.repository

import com.movie.android.datamodel.MoviesList
import com.movie.android.retrofit.ApiClient
import com.movie.android.retrofit.ApiServices
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.runBlocking
import retrofit2.Response

class MainRepository(
    private val apiClient: ApiClient
) {


    private val API_KEY_FOR_MOVIES = "092333c957064bbee6538fec7c7fa9c0"
    private val language = "en-US"
    private val page = "1"

    fun getResponseFromRetrofit(): Observable<MoviesList> = runBlocking {

        return@runBlocking Observable.just(getMoviesListFromServer().body())

    }

    private fun getMoviesListFromServer(): Response<MoviesList> = runBlocking {

        return@runBlocking apiClient.retrofitInit()
            .create(ApiServices::class.java)
            .getPopularMoviesList(API_KEY_FOR_MOVIES, language, page)

    }

}