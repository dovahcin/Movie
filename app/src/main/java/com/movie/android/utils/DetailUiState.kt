package com.movie.android.utils

sealed class DetailUiState {

    data class Success(val detailsDataModel: DetailsDataModel = DetailsDataModel()): DetailUiState()
    data class Failure(val exception: Throwable): DetailUiState()
    object Loading: DetailUiState()

}
