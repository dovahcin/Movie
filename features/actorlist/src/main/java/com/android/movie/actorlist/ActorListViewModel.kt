package com.android.movie.actorlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.android.data.ActorListRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ActorListViewModel(
    private val repository: ActorListRepository,
) : ViewModel() {

    companion object {
        private const val ACTOR_LIST_KEY = "ActorList"
    }

    private val _uiState = MutableStateFlow<ActorListUiState>(ActorListUiState.Success())
    val uiState: StateFlow<ActorListUiState> = _uiState

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = ActorListUiState.Failure(exception)
    }

    fun getActors(page: Int) {

        if (_uiState.value is ActorListUiState.Success) {
            when {
                ((_uiState.value as ActorListUiState.Success).actorListModel.isInitialValue()) -> {
                    fetchData(page)
                }
                page != 1 -> {
                    fetchData(page)
                }
            }
        }
    }

    private fun fetchData(page: Int) {
        viewModelScope.launch(coroutineExceptionHandler) {
            repository.getDataForActorList(page)
                .onStart { _uiState.value = ActorListUiState.Loading }
                .collect { actors ->
                    _uiState.value = ActorListUiState.Success(actors)
                }
        }


    }



}