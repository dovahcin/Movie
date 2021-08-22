package com.movie.android.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ExploreViewModel(
    private val exploreRepository: ExploreRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow<ExploreUiState>(ExploreUiState.Success())
    val uiState: StateFlow<ExploreUiState> = _uiState

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = ExploreUiState.Failure(exception)
    }

    fun loadDataForExplore(page: Int) {
        viewModelScope.launch(coroutineExceptionHandler) {
            exploreRepository.getDataForExplorePage(page)
                .onStart { _uiState.value = ExploreUiState.Loading }
                .collect { movies ->
                    _uiState.value = ExploreUiState.Success(movies)
                }
        }
    }



}