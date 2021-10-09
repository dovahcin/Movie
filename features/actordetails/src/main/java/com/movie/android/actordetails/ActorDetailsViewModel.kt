package com.movie.android.actordetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.android.data.ActorDetailsRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ActorDetailsViewModel(
    private val actorDetailsRepository: ActorDetailsRepository,
    private val saveState: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val ACTOR_DETAILS_KEY = "ActorDetailsVM"
    }

    private val _uiState = MutableStateFlow<ActorDetailUiState>(ActorDetailUiState.Success())
    val uiState: StateFlow<ActorDetailUiState> = _uiState

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = ActorDetailUiState.Failure(exception)
    }

    fun loadDataForActorDetails(personId: Int, page: Int) {
        if (saveState.get<ActorDetailUiState>(ACTOR_DETAILS_KEY) == null) {
            viewModelScope.launch(coroutineExceptionHandler) {
                actorDetailsRepository.getActorDetails(personId, page)
                    .onStart { _uiState.value = ActorDetailUiState.Loading }
                    .collect { actorDataModel ->
                        _uiState.value = ActorDetailUiState.Success(actorDataModel)
                        saveState[ACTOR_DETAILS_KEY] = ActorDetailUiState.Success(actorDataModel)
                    }
            }
        } else {
            _uiState.value = saveState.get<ActorDetailUiState>(ACTOR_DETAILS_KEY)!!
        }
    }

    override fun onCleared() {
        super.onCleared()
        saveState[ACTOR_DETAILS_KEY] = null
    }

}