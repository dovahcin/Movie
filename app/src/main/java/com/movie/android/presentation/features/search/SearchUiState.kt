package com.movie.android.presentation.features.search

import com.movie.android.domain.SearchDataModel

sealed class SearchUiState {
    data class Success(val searchDataModel: SearchDataModel = SearchDataModel()) : SearchUiState()
    data class Failure(val exception: Throwable) : SearchUiState()
    object Loading : SearchUiState()
}