package com.movie.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.android.data.MainRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Success())
    val uiState: StateFlow<MainUiState> = _uiState

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = MainUiState.Error(exception)
    }

    fun loadDataForRecyclerView() {
        viewModelScope.launch(coroutineExceptionHandler) {
            mainRepository.getPopularMovies()
                .onStart { _uiState.value = MainUiState.Loading }
                .collect {movieResult->
                    _uiState.value = MainUiState.Success(movieResult.results)
                }
        }
    }

}