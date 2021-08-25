package com.movie.android.presentation.features.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.android.data.DetailsRepository
import com.movie.android.presentation.features.details.DetailUiState.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val detailsRepository: DetailsRepository,
    private val state: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val DETAILS_STATE = "DetailsState"
        private const val DETAILS_MOVIE_ID = "DetailMovieId"
    }

    private val _uiState = MutableStateFlow<DetailUiState>(
        Success()
    )
    val uiState: StateFlow<DetailUiState> = _uiState

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = Failure(exception)
    }

    fun loadDataForDetails(movieId: Int) {

        viewModelScope.launch(coroutineExceptionHandler) {
            detailsRepository.getDataForDetailedPage(movieId)
                .onStart { _uiState.value = Loading }
                .collect { dataModel ->
                    _uiState.value = Success(dataModel)
                }
        }


    }


}