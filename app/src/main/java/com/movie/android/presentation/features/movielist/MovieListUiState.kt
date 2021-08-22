package com.movie.android.presentation.features.movielist

import com.movie.android.domain.Movie

sealed class MovieListUiState {

    data class Success(val movies: MutableList<Movie> = mutableListOf()): MovieListUiState()
    data class Error(val exception: Throwable): MovieListUiState()
    object Loading: MovieListUiState()

}