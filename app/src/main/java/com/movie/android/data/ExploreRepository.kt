package com.movie.android.data

import com.movie.android.data.network.ApiServices
import com.movie.android.features.popular.explore.Explore
import com.movie.android.features.popular.explore.ExploreItem.HorizontalListPopular
import com.movie.android.features.popular.explore.ExploreItem.HorizontalListUpcoming
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ExploreRepository(private val api: ApiServices) {

    fun getDataForExplorePage(page: Int) = flow {
        val result = Explore()
        val popular = api.getPopularMoviesList(page.toString())
        val upcoming = api.getUpcomingMovies(page.toString())

        result.items += HorizontalListPopular(
            "Popular", false, popular.results.take(10)
        )
        result.items += HorizontalListUpcoming(
            "Upcoming", false, upcoming.results.take(10)
        )

        emit(result)

    }.flowOn(Dispatchers.IO)

}