package com.movie.android.presentation.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.android.data.SearchRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Success())
    val uiState: StateFlow<SearchUiState> = _uiState

    val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = SearchUiState.Failure(exception)
    }

    fun loadDataForSearchList(query: String) {
        viewModelScope.launch(exceptionHandler) {
            searchRepository.getQueryResults(query)
                .onStart { _uiState.value = SearchUiState.Loading }
                .collect { queryResults ->
                    _uiState.value = SearchUiState.Success(queryResults)
                }
        }
    }

}