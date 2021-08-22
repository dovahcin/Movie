package com.movie.android.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class PopularViewModel(private val repository: PopularRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<PopularUiState>(PopularUiState.Success())
    val uiState: StateFlow<PopularUiState> = _uiState

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = PopularUiState.Error(exception)
    }

    init {
        getMovies(1)
    }

    fun getMovies(page: Int) {
        viewModelScope.launch(coroutineExceptionHandler) {
            repository.getMovies(page)
                .onStart { _uiState.value = PopularUiState.Loading }
                .collect {movieResult->
                    _uiState.value = PopularUiState.Success(movieResult.results as MutableList)
                }
        }
    }

}