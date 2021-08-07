package com.movie.android.utils

import com.movie.android.domain.Movie

sealed class MainUiState {
    data class Success(val movies: List<Movie> = emptyList()): MainUiState()
    data class Error(val exception: Throwable): MainUiState()
    object Loading: MainUiState()
}