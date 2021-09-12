package com.movie.android.presentation.features.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.android.data.SearchRepository
import com.movie.android.data.db.HistoryDatabase
import com.movie.android.domain.History
import com.movie.android.domain.SearchDataModel
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
    private val historyDatabase: HistoryDatabase,
    private val state: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val SEARCH_KEY = "SearchState"
    }

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Success())
    val uiState: StateFlow<SearchUiState> = _uiState

    private var searchDataModel = SearchDataModel()

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = SearchUiState.Failure(exception)
    }

    fun loadDataForSearchList(query: String) {
        if (state.get<SearchUiState.Success>(SEARCH_KEY) == null) {

            viewModelScope.launch(exceptionHandler) {
                searchRepository.getDataForLists(query)
                    .onStart { _uiState.value = SearchUiState.Loading }
                    .collect { dataModel ->
                        _uiState.value = SearchUiState.Success(dataModel)
                        searchDataModel = dataModel
                    }
            }
        } else {
            _uiState.value = state.get<SearchUiState>(SEARCH_KEY)!!
        }
    }

    fun insertTitles(history: History) {
        CoroutineScope(Dispatchers.IO).launch {
            historyDatabase.searchHistory().insert(history)
        }
    }

    fun deleteTitle(movieId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            historyDatabase.searchHistory().delete(movieId)
        }
    }

    fun navigated() {
        state.set(SEARCH_KEY, SearchUiState.Success(searchDataModel))
    }
}