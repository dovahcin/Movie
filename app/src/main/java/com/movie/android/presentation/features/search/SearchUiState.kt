package com.movie.android.presentation.features.search

import com.movie.android.domain.MovieList

sealed class SearchUiState {
    data class Success(val movies: MovieList = MovieList()): SearchUiState()
    data class Failure(val exception: Throwable): SearchUiState()
    object Loading: SearchUiState()
}