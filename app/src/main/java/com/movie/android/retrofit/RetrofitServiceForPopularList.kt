package com.movie.android.retrofit

import androidx.lifecycle.MutableLiveData
import com.movie.android.datamodel.MoviesList
import kotlinx.coroutines.runBlocking


object RetrofitServiceForPopularList {

    val mutableLiveDataForRepository = MutableLiveData<MoviesList>()

    private val TAG = "RetrofitPopular"

    private const val API_KEY_FOR_MOVIES = "092333c957064bbee6538fec7c7fa9c0"
    private const val language = "en-US"
    private const val page = "1"

    fun getPopularMoviesList() = runBlocking {
        val response = ApiClient().retrofitInit()
            .create(ApiServices::class.java)
            .getPopularMoviesList(API_KEY_FOR_MOVIES, language, page)

        mutableLiveDataForRepository.value = response.body()
    }

}