package com.movie.android.presentation.features.explore

import com.movie.android.domain.Explore

sealed class ExploreUiState {
    data class Success(val explore: Explore = Explore()): ExploreUiState()
    data class Failure(val exception: Throwable): ExploreUiState()
    object Loading: ExploreUiState()
}
