package com.movie.android.features.details


sealed class DetailUiState {

    data class Success(val detailsDataModel: DetailsDataModel = DetailsDataModel()): DetailUiState()
    data class Failure(val exception: Throwable): DetailUiState()
    object Loading: DetailUiState()

}
