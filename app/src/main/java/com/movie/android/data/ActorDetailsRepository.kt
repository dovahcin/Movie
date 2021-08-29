package com.movie.android.data

import com.movie.android.data.network.ApiServices
import com.movie.android.domain.ActorsDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ActorDetailsRepository(private val api: ApiServices) {

    fun getActorDetails(personId: Int, page: Int) = flow {
        val actorDetails = api.getActorDetails(personId.toString())
        val actorList = api.getArtists(page.toString())
        emit(ActorsDataModel(
            actorDetails,
            actorList.results
        ))
    }.flowOn(Dispatchers.IO)

}