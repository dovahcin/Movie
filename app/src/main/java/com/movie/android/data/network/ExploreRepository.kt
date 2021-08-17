package com.movie.android.data.network

import com.movie.android.domain.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ExploreRepository(private val api: ApiServices) {

    fun getDataForExplorePage(page: Int) = flow {
        val popular = api.getPopularMoviesList(page.toString())
        val upcoming = api.getUpcomingMovies()

        val popularListType = Movie.createPopular(popular.results)
        val upcomingTitleType = Movie.createUpcoming(upcoming.results)

        val dataForEmit = (mutableListOf(popularListType) + mutableListOf(upcomingTitleType)).toMutableList()

        emit(dataForEmit)

    }.flowOn(Dispatchers.IO)

}