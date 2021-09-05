package com.movie.android.data

import com.movie.android.data.network.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchRepository(private val api: ApiServices) {

    fun getQueryResults(query: String) = flow {
        emit(api.getQueryResults(query))
    }.flowOn(Dispatchers.IO)

}