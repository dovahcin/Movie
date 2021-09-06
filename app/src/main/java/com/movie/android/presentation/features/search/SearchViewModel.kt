package com.movie.android.presentation.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.android.data.SearchRepository
import com.movie.android.data.db.HistoryDatabase
import com.movie.android.domain.History
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchRepository: SearchRepository,
    private val historyDatabase: HistoryDatabase
) : ViewModel() {
    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Success())
    val uiState: StateFlow<SearchUiState> = _uiState

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = SearchUiState.Failure(exception)
    }

    fun loadDataForSearchList(query: String) {
        viewModelScope.launch(exceptionHandler) {
            searchRepository.getDataForLists(query)
                .onStart { _uiState.value = SearchUiState.Loading }
                .collect { searchDataModel ->
                    _uiState.value = SearchUiState.Success(searchDataModel)
                }
        }
    }

    fun insertTitles(history: History) {
        CoroutineScope(Dispatchers.IO).launch {
            historyDatabase.historyDao().insert(history)
        }
    }

    fun deleteTitle(movieId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            historyDatabase.historyDao().delete(movieId)
        }
    }

}