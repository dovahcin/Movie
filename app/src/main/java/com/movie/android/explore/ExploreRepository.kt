package com.movie.android.explore

import com.movie.android.data.network.ApiServices
import com.movie.android.domain.Movie
import com.movie.android.explore.ExploreItem.HorizontalListPopular
import com.movie.android.explore.ExploreItem.HorizontalListUpcoming
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ExploreRepository(private val api: ApiServices) {

    fun getDataForExplorePage(page: Int) = flow {
        val result = Explore()
        val popular = api.getPopularMoviesList(page.toString())
        val upcoming = api.getUpcomingMovies()

        result.items += HorizontalListPopular(
            "Popular", false, popular.results.take(10) + Movie.createShowMore()
        )
        result.items += HorizontalListUpcoming(
            "Upcoming", false, upcoming.results.take(10) + Movie.createShowMore()
        )

        emit(result)

    }.flowOn(Dispatchers.IO)

}