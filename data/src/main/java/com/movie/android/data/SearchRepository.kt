package com.movie.android.data

import com.movie.android.data.db.SearchHistoryDao
import com.movie.android.data.network.ApiServices
import com.movie.android.domain.MovieList
import com.movie.android.domain.SearchDataModel
import com.movie.android.domain.SearchHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchRepository(private val api: ApiServices, private val movieDatabase: SearchHistoryDao) {

    fun getDataForLists(query: String) = flow {

        val histories = movieDatabase.getAll()

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

    fun addSearchHistory(history: SearchHistory) = flow {
        movieDatabase.insert(history)
        emit(SearchDataModel(
            MovieList(),
            histories = movieDatabase.getAll()
        ))
    }.flowOn(Dispatchers.IO)

    fun removeSearchHistory(historyId: Int) = flow {
        movieDatabase.delete(historyId)
        emit(SearchDataModel(
            MovieList(),
            histories = movieDatabase.getAll()
        ))
    }.flowOn(Dispatchers.IO)

}