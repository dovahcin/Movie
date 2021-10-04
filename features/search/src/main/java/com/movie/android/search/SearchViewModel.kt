package com.movie.android.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.android.data.SearchRepository
import com.movie.android.domain.SearchHistory
import com.movie.android.domain.SearchDataModel
import com.movie.android.domain.SearchHistory
import com.movie.android.search.SearchUiState.Failure
import com.movie.android.search.SearchUiState.Loading
import com.movie.android.search.SearchUiState.Success
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchRepository: SearchRepository,
    private val state: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val SEARCH_KEY = "SearchState"
    }

    private val _uiState = MutableStateFlow<SearchUiState>(Success())
    val uiState: StateFlow<SearchUiState> = _uiState

    private var searchDataModel = SearchDataModel()

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = Failure(exception)
    }

    fun loadDataForSearchList(query: String, isChangeable: Boolean = false) {
        if (state.get<SearchUiState.Success>(SEARCH_KEY) == null) {
            viewModelScope.launch(exceptionHandler) {
                searchRepository.getDataForLists(query)
                    .onStart { _uiState.value = Loading }
                    .collect { dataModel ->
                        searchDataModel = dataModel
                        _uiState.value = Success(searchDataModel)
                    }
            }
        } else {
            _uiState.value = state.get<SearchUiState>(SEARCH_KEY)!!
        }
    }

    fun saveSearchHistory(history: SearchHistory) {
        viewModelScope.launch {
            searchRepository.addSearchHistory(history)
                .collect {
                    _uiState.value = Success(searchDataModel.reduce(it))
                }
        }
    }

    fun deleteSearchHistory(historyId: Int) {
        viewModelScope.launch {
            searchRepository.removeSearchHistory(historyId)
                .collect {
                    _uiState.value = Success(searchDataModel.reduce(it))
                }
        }
    }

    fun saveInstanceState() {
        state[SEARCH_KEY] = SearchUiState.Success(searchDataModel)
    }

    fun deleteInstanceState() {
        state[SEARCH_KEY] = null
    }

    override fun onCleared() {
        super.onCleared()
        deleteInstanceState()
    }
}