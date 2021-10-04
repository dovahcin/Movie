package com.movie.android.movielist

import android.os.Parcelable
import com.movie.android.domain.MovieList
import com.movie.android.domain.movieListInitialValue
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

sealed class MovieListUiState {
    @Parcelize
    data class Success(val movies: @RawValue MovieList = movieListInitialValue): MovieListUiState(),
        Parcelable

    data class Error(val exception: Throwable): MovieListUiState()
    object Loading: MovieListUiState()

}