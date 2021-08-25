package com.movie.android.presentation.features.details

import android.os.Parcelable
import com.movie.android.domain.DetailsDataModel
import com.movie.android.domain.detailsInitialValue
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

sealed class DetailUiState {
    @Parcelize
    data class Success(val detailsDataModel: @RawValue DetailsDataModel = detailsInitialValue): DetailUiState(),
        Parcelable
    data class Failure(val exception: Throwable): DetailUiState()
    object Loading: DetailUiState()

}
