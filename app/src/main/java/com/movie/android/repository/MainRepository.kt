package com.movie.android.repository

import com.movie.android.datamodel.MoviesList
import com.movie.android.retrofit.RetrofitServiceForPopularList
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.runBlocking

class MainRepository(
    private val retrofitServiceForPopularList: RetrofitServiceForPopularList
) {

    fun getData(): Observable<MoviesList> = runBlocking {

        retrofitServiceForPopularList.getPopularMoviesList()

        return@runBlocking Observable.just(
            retrofitServiceForPopularList.mutableLiveDataForRepository.value
        )

    }

}