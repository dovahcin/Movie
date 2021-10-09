package com.movie.android.explore

import android.os.Parcelable
import com.movie.android.domain.Explore
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


sealed class ExploreUiState {

    @Parcelize
    data class Success(val explore: @RawValue Explore = Explore()): ExploreUiState(), Parcelable
    data class Failure(val exception: Throwable): ExploreUiState()
    object Loading: ExploreUiState()
}
