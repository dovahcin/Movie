package com.movie.android.data

import com.movie.android.data.db.HistoryDatabase
import com.movie.android.data.network.ApiServices
import com.movie.android.domain.MovieList
import com.movie.android.domain.SearchDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchRepository(private val api: ApiServices,private val historyDatabase: HistoryDatabase) {

    fun getDataForLists(query: String) = flow {


        val histories = historyDatabase.searchHistory().getAllMovieTitles()

        val queryResult = if (query.isEmpty()) {
            MovieList()
        } else {
            api.getQueryResults(query)
        }
        emit(SearchDataModel(
            queryResult,
            histories
        ))
    }.flowOn(Dispatchers.IO)
}