package com.movie.android.utils

import com.movie.android.domain.details.Details

sealed class DetailUiState {

    data class Success(val detailsDataModel: DetailsDataModel? = null): DetailUiState()
    data class Failure(val exception: Throwable): DetailUiState()
    object Loading: DetailUiState()

}
