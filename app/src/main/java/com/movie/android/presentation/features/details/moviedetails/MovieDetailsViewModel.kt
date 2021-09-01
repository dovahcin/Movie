package com.movie.android.presentation.features.details.moviedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.android.data.MovieDetailsRepository
import com.movie.android.presentation.features.details.moviedetails.MovieDetailUiState.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val detailsRepository: MovieDetailsRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<MovieDetailUiState>(
        Success()
    )
    val uiState: StateFlow<MovieDetailUiState> = _uiState

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = Failure(exception)
    }

    fun loadDataForDetails(movieId: Int) {
        if (uiState.value is Success){
            if ((uiState.value as Success).detailsDataModel.isInitialValue()){
                viewModelScope.launch(coroutineExceptionHandler) {
                    detailsRepository.getDataForDetailedPage(movieId)
                        .onStart { _uiState.value = Loading }
                        .collect { dataModel ->
                            _uiState.value = Success(dataModel)
                        }
                }
            }
        }


    }


}