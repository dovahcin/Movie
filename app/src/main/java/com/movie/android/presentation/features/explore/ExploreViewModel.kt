package com.movie.android.presentation.features.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.android.data.ExploreRepository
import com.movie.android.presentation.features.explore.ExploreUiState.Failure
import com.movie.android.presentation.features.explore.ExploreUiState.Loading
import com.movie.android.presentation.features.explore.ExploreUiState.Success
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ExploreViewModel(
    private val exploreRepository: ExploreRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow<ExploreUiState>(Success())
    val uiState: StateFlow<ExploreUiState> = _uiState

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = Failure(exception)
    }

    fun loadDataForExplore(page: Int) {
        viewModelScope.launch(coroutineExceptionHandler) {
            exploreRepository.getDataForExplorePage(page)
                .onStart { _uiState.value = Loading }
                .collect { movies ->
                    _uiState.value = Success(movies)
                }
        }
    }



}