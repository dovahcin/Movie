package com.ilkinyazar.explore

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilkinyazar.explore.ExploreUiState.*
import com.movie.android.data.ExploreRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ExploreViewModel(
    private val exploreRepository: ExploreRepository,
    private val state: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val EXPLORE_KEY = "ExploreUiState"
    }

    private var _uiState = MutableStateFlow<ExploreUiState>(Success())
    val uiState: StateFlow<ExploreUiState> = _uiState

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = Failure(exception)
    }

    fun loadDataForExplore(page: Int) {
        if (state.get<Success>(EXPLORE_KEY) == null) {
            viewModelScope.launch(coroutineExceptionHandler) {
                exploreRepository.getDataForExplorePage(page)
                    .onStart { _uiState.value = Loading }
                    .collect { results ->
                        _uiState.value = Success(results)
                        state.set(EXPLORE_KEY, Success(results))
                    }

            }
        } else {
            _uiState.value = state.get<ExploreUiState>(EXPLORE_KEY)!!
        }
    }



}