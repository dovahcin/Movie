package com.movie.android.utils

import com.movie.android.domain.Movie

sealed class MainUiState {

    data class Success(val movies: MutableList<Movie> = mutableListOf()): MainUiState()
    data class Error(val exception: Throwable): MainUiState()
    object Loading: MainUiState()

}