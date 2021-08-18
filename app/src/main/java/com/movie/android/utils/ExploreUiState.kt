package com.movie.android.utils

import com.movie.android.domain.explore.Explore

sealed class ExploreUiState {
    data class Success(val explore: Explore = Explore()): ExploreUiState()
    data class Failure(val exception: Throwable): ExploreUiState()
    object Loading: ExploreUiState()
}
