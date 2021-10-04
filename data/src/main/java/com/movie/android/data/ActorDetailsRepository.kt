package com.movie.android.data

import com.movie.android.data.MovieListRepository.Companion.DEFAULT_PAGE
import com.movie.android.data.network.ApiServices
import com.movie.android.domain.ActorsDataModel
import com.movie.android.domain.ExploreItem
import com.movie.android.domain.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ActorDetailsRepository(private val api: ApiServices) {

    fun getActorDetails(personId: Int, page: Int) = flow {
        val actorDetails = api.getActorDetails(personId.toString())
        val actorList = api.getArtists(page.toString())
        val actorMovies = api.getActorMovies(personId.toString(), DEFAULT_PAGE.toString())
            .also {
                it.id = ExploreItem.Type.ActorMovieList.ordinal
            }

        if (actorMovies.results.size >= 10) {
            actorMovies.results =  (actorMovies.results.take(10) + Movie.createShowMore()).toMutableList()
        }

        emit(ActorsDataModel(
            actorDetails,
            actorList.results,
            actorMovies
        ))
    }.flowOn(Dispatchers.IO)

}