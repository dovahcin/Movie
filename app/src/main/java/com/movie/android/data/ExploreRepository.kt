package com.movie.android.data

import com.movie.android.data.network.ApiServices
import com.movie.android.domain.explore.Explore
import com.movie.android.domain.explore.ExploreItem.HorizontalList
import com.movie.android.domain.explore.ExploreItem.VerticalList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ExploreRepository(private val api: ApiServices) {

    fun getDataForExplorePage(page: Int) = flow {
        val result = Explore()
        val popular = api.getPopularMoviesList(page.toString())
        val upcoming = api.getUpcomingMovies()

        result.items += HorizontalList(popular.results)
        result.items += VerticalList(upcoming.results)

        emit(result)

    }.flowOn(Dispatchers.IO)

}