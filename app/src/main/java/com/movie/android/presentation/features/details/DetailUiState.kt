package com.movie.android.presentation.features.details

import com.movie.android.domain.DetailsDataModel

sealed class DetailUiState {

    data class Success(val detailsDataModel: DetailsDataModel = DetailsDataModel()): DetailUiState()
    data class Failure(val exception: Throwable): DetailUiState()
    object Loading: DetailUiState()

}
