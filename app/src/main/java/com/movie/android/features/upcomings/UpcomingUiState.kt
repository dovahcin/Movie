package com.movie.android.features.upcomings

import com.movie.android.domain.Movie

sealed class UpcomingUiState {
    data class Success(val movies: List<Movie> = mutableListOf()) : UpcomingUiState()
    data class Failure(val exception: Throwable) : UpcomingUiState()
    object Loading : UpcomingUiState()
}