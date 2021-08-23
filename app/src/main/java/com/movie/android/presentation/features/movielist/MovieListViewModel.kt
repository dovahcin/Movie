package com.movie.android.presentation.features.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.android.data.MovieListRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MovieListViewModel(private val repository: MovieListRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<MovieListUiState>(MovieListUiState.Success())
    val uiState: StateFlow<MovieListUiState> = _uiState

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = MovieListUiState.Error(exception)
    }

    fun getMovies(listId: Int, page: Int) {
        viewModelScope.launch(coroutineExceptionHandler) {
            repository.getMovies(listId, page)
                .onStart { _uiState.value = MovieListUiState.Loading }
                .collect { movieResult ->
                    _uiState.value = MovieListUiState.Success(movieResult.results)
                }
        }
    }

}