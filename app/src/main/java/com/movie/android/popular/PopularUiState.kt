package com.movie.android.popular

import com.movie.android.domain.Movie

sealed class PopularUiState {

    data class Success(val movies: MutableList<Movie> = mutableListOf()): PopularUiState()
    data class Error(val exception: Throwable): PopularUiState()
    object Loading: PopularUiState()

}