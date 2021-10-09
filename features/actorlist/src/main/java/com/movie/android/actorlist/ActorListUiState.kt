package com.movie.android.actorlist

import android.os.Parcelable
import com.movie.android.domain.ActorList
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

sealed class ActorListUiState() {
    @Parcelize
    data class Success(val actorListModel: @RawValue ActorList = ActorList()): ActorListUiState(),
        Parcelable
    data class Failure(val exception: Throwable): ActorListUiState()
    object Loading: ActorListUiState()

}
