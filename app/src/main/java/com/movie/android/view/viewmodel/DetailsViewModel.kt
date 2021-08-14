package com.movie.android.view.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.android.data.DetailsRepository
import com.movie.android.utils.DetailUiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.*
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
                .buffer()
                .collect { dataModel ->
                    Log.d("fetchScope", "in fetch scope dataModel : $dataModel")
                    _uiState.value = DetailUiState.Success(dataModel)
                }

        }

    }

}