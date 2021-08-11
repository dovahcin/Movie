package com.movie.android.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.android.data.DetailsRepository
import com.movie.android.domain.details.Details
import com.movie.android.domain.details.Recommendations
import com.movie.android.domain.details.Similarities
import com.movie.android.utils.DetailUiState
import com.movie.android.utils.DetailsDataModel
import com.movie.android.utils.MainUiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val detailsRepository: DetailsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Success())
    val uiState: StateFlow<DetailUiState> = _uiState

    lateinit var detailsForModel: Details
    lateinit var recommendationsForModel: Recommendations
    lateinit var similaritiesForModel: Similarities

    private val coroutineExceptionHandler
    = CoroutineExceptionHandler { _ , exception ->

        _uiState.value = DetailUiState.Failure(exception)

    }

    private fun loadDataForDetails(currentPage: String, movieId: String) {

        viewModelScope.launch(coroutineExceptionHandler) {
            detailsRepository.getDataForDetailedPage(movieId)
                .onStart { _uiState.value = DetailUiState.Loading }
                .collect { details ->
                  detailsForModel = details
                }
            detailsRepository.getSimilars(currentPage, movieId)
                .onStart { _uiState.value = DetailUiState.Loading }
                .collect { similarity ->
                    similaritiesForModel = similarity
                }
            detailsRepository.getRecommendations(currentPage, movieId)
                .onStart { _uiState.value = DetailUiState.Loading }
                .collect { recommendations ->
                    recommendationsForModel = recommendations
                }


            _uiState.value = DetailUiState.Success(DetailsDataModel(
                similaritiesForModel, recommendationsForModel, detailsForModel
            ))
        }

    }

}