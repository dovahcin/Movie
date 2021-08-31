package com.movie.android.presentation.features.details.actordetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.android.data.ActorDetailsRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ActorDetailsViewModel(private val actorDetailsRepository: ActorDetailsRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<ActorDetailUiState>(ActorDetailUiState.Success())
    val uiState: StateFlow<ActorDetailUiState> = _uiState

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = ActorDetailUiState.Failure(exception)
    }

    fun loadDataForActorDetails(personId: Int, page: Int) {
        viewModelScope.launch(coroutineExceptionHandler) {
            actorDetailsRepository.getActorDetails(personId, page)
                .onStart { _uiState.value = ActorDetailUiState.Loading }
                .collect { actorDetails ->
                    _uiState.value = ActorDetailUiState.Success(actorDetails)
                    Log.d("ActorVm", "Reached")
                }
        }

    }

}