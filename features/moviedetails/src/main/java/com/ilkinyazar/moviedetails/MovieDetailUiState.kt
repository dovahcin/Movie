package com.ilkinyazar.moviedetails

import android.os.Parcelable
import com.movie.android.domain.DetailsDataModel
import com.movie.android.domain.detailsInitialValue
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

sealed class MovieDetailUiState {
    @Parcelize
    data class Success(val detailsDataModel: @RawValue DetailsDataModel = detailsInitialValue): MovieDetailUiState(),
        Parcelable
    data class Failure(val exception: Throwable): MovieDetailUiState()
    object Loading: MovieDetailUiState()

}
