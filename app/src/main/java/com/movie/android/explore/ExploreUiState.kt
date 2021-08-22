package com.movie.android.explore

sealed class ExploreUiState {
    data class Success(val explore: Explore = Explore()): ExploreUiState()
    data class Failure(val exception: Throwable): ExploreUiState()
    object Loading: ExploreUiState()
}
