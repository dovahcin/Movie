package com.movie.android.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.android.data.PopularMovieRepository
import com.movie.android.utils.MainUiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PopularMovieRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Success())
    val uiState: StateFlow<MainUiState> = _uiState

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = MainUiState.Error(exception)
    }

    init {
        getMovies(1)
    }

    fun getMovies(page: Int) {
        viewModelScope.launch(coroutineExceptionHandler) {
            repository.getMovies(page)
                .onStart { _uiState.value = MainUiState.Loading }
                .collect {movieResult->
                    _uiState.value = MainUiState.Success(movieResult.results as MutableList)
                }
        }
    }

}