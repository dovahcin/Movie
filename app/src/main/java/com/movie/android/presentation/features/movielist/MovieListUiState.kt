package com.movie.android.presentation.features.movielist

import android.os.Parcelable
import com.movie.android.domain.Movie
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

sealed class MovieListUiState {
    @Parcelize
    data class Success(val movies: @RawValue MutableList<Movie> = mutableListOf()): MovieListUiState(),
        Parcelable

    data class Error(val exception: Throwable): MovieListUiState()
    object Loading: MovieListUiState()

}