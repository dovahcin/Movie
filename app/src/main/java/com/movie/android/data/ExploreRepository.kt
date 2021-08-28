package com.movie.android.data

import com.movie.android.data.network.ApiServices
import com.movie.android.domain.Explore
import com.movie.android.domain.ExploreItem.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ExploreRepository(private val api: ApiServices) {

    fun getDataForExplorePage(page: Int) = flow {
        val result = Explore()
        val popular = api.getPopularMoviesList(page.toString())
        val upcoming = api.getUpcomingMovies(page.toString())
        val artists = api.getArtists(page.toString())

        result.items += VerticalMovieList(
            "Popular", false, popular.results.take(10)
        )
        result.items += HorizontalMovieList(
            "Upcoming", false, upcoming.results.take(10)
        )
        result.items += Artists(
            "Artists", false, artists.results.take(10)
        )

        emit(result)

    }.flowOn(Dispatchers.IO)

}