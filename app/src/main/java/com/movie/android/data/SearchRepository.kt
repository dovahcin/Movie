package com.movie.android.data

import com.movie.android.data.db.HistoryDatabase
import com.movie.android.data.network.ApiServices
import com.movie.android.domain.SearchDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchRepository(private val api: ApiServices,private val historyDatabase: HistoryDatabase) {

    fun getDataForLists(query: String) = flow {

        val queryResult = api.getQueryResults(query)
        val histories = historyDatabase.historyDao().getAllMovieTitles()

        emit(SearchDataModel(
          queryResult,
          histories
        ))

    }.flowOn(Dispatchers.IO)
}