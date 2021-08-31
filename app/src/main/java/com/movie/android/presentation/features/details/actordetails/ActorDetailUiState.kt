package com.movie.android.presentation.features.details.actordetails

import com.movie.android.domain.ActorsDataModel

sealed class ActorDetailUiState {
    data class Success(val actorsDataModel: ActorsDataModel = ActorsDataModel()): ActorDetailUiState()
    data class Failure(val exception: Throwable): ActorDetailUiState()
    object Loading: ActorDetailUiState()
}