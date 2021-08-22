package com.movie.android.features.upcomings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.android.data.UpcomingRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class UpcomingViewModel(
    private val upcomingRepository: UpcomingRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow<UpcomingUiState>(UpcomingUiState.Success())
    val uiState: StateFlow<UpcomingUiState> = _uiState

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = UpcomingUiState.Failure(exception)
    }

    fun loadDataForList(page: Int) {
        viewModelScope.launch(coroutineExceptionHandler) {
            upcomingRepository.getMovies(page)
                .onStart { _uiState.value = UpcomingUiState.Loading }
                .collect { upcomings ->
                    _uiState.value = UpcomingUiState.Success(upcomings.results)
                }
        }
    }

}