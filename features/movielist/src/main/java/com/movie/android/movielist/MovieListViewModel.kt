package com.movie.android.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.android.data.MovieListRepository
import com.movie.android.movielist.MovieListUiState.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val repository: MovieListRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<MovieListUiState>(Success())
    val uiState: StateFlow<MovieListUiState> = _uiState

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = Error(exception)
    }

    fun getMovies(listId: Int, page: Int, movieId: Int) {
        if (uiState.value is Success) {
            when {
                (uiState.value as Success).movies.isInitialized() -> {
                    launchScope(listId, page, movieId)
                }
                page != 1 -> {
                   launchScope(listId, page, movieId)
                }
            }
        }

    }
    private fun launchScope(listId: Int, page: Int, movieId: Int) {
        viewModelScope.launch(coroutineExceptionHandler) {
            repository.getMovies(listId, page, movieId)
                .onStart { _uiState.value = Loading }
                .collect { movieResult ->
                    _uiState.value = Success(movieResult)
                }

        }
    }
}