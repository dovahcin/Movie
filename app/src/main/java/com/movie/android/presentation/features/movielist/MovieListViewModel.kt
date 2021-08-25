package com.movie.android.presentation.features.movielist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.android.data.MovieListRepository
import com.movie.android.presentation.features.movielist.MovieListUiState.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val repository: MovieListRepository,
    private val state: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val MOVIELIST_KEY = "MovieList"
    }

    private val _uiState = MutableStateFlow<MovieListUiState>(Success())
    val uiState: StateFlow<MovieListUiState> = _uiState

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = Error(exception)
    }


    fun getMovies(listId: Int, page: Int, movieId: Int) {
        if (uiState.value is Success) {
            if ((uiState.value as Success).movies.isInitialized()) {
                viewModelScope.launch(coroutineExceptionHandler) {
                    repository.getMovies(listId, page, movieId)
                        .onStart { _uiState.value = Loading }
                        .collect { movieResult ->
                            _uiState.value = Success(movieResult)
                        }

                }
            }
        }
    }

}