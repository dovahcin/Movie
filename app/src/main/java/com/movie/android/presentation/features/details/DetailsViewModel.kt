package com.movie.android.presentation.features.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.android.data.DetailsRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val detailsRepository: DetailsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<DetailUiState>(
        DetailUiState.Success()
    )
    val uiState: StateFlow<DetailUiState> = _uiState

    private val coroutineExceptionHandler
    = CoroutineExceptionHandler { _ , exception ->
        _uiState.value = DetailUiState.Failure(exception)
    }

    fun loadDataForDetails(movieId: Int) {

        viewModelScope.launch(coroutineExceptionHandler) {
            detailsRepository.getDataForDetailedPage(movieId)
                .onStart { _uiState.value = DetailUiState.Loading }
                .collect { dataModel ->
                    _uiState.value = DetailUiState.Success(dataModel)
                }
        }

    }

}