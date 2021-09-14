package com.movie.android.presentation.features.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.android.data.SearchRepository
import com.movie.android.data.db.MovieDatabase
import com.movie.android.domain.SearchHistory
import com.movie.android.domain.SearchDataModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchRepository: SearchRepository,
    private val movieDatabase: MovieDatabase,
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

    fun insertTitles(history: SearchHistory) {
        viewModelScope.launch {
            movieDatabase.searchHistory().insert(history)
        }
    }

    fun deleteSearchHistory(historyId: Int) {
        viewModelScope.launch {
            movieDatabase.searchHistory().delete(historyId)
        }
    }

    fun navigated() {
        state.set(SEARCH_KEY, SearchUiState.Success(searchDataModel))
    }
}