package com.movie.android.utils

import com.movie.android.domain.Movie

sealed class ExploreUiState {
    data class Success(val movies: MutableList<Movie> = mutableListOf()): ExploreUiState()
    data class Failure(val exception: Throwable): ExploreUiState()
    object Loading: ExploreUiState()
}
