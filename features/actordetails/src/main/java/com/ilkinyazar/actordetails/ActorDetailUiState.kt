package com.ilkinyazar.actordetails

import android.os.Parcelable
import com.movie.android.domain.ActorsDataModel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


sealed class ActorDetailUiState {
    @Parcelize
    data class Success(val actorsDataModel: @RawValue ActorsDataModel = ActorsDataModel()) :
        ActorDetailUiState(), Parcelable

    data class Failure(val exception: Throwable) : ActorDetailUiState()
    object Loading : ActorDetailUiState()
}