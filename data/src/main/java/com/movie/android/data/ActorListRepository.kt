package com.movie.android.data

import com.movie.android.data.network.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ActorListRepository(private val api: ApiServices) {

    fun getDataForActorList(page: Int) = flow {

        emit(api.getArtists(page.toString()))

    }.flowOn(Dispatchers.IO)

}