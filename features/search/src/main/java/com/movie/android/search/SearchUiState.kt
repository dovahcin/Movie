package com.movie.android.search

import android.os.Parcelable
import com.movie.android.domain.SearchDataModel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

sealed class SearchUiState {
    @Parcelize
    data class Success(val searchDataModel: @RawValue SearchDataModel = SearchDataModel()) : SearchUiState(),
        Parcelable

    data class Failure(val exception: Throwable) : SearchUiState()
    object Loading : SearchUiState()
}