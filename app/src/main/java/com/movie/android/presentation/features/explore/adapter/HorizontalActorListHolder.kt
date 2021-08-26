package com.movie.android.presentation.features.explore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.databinding.ItemActorListHorizontalBinding

class HorizontalActorListHolder(
    private val binding: ItemActorListHorizontalBinding,
    private val actorClick: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup, actorClick: () -> Unit) =
            HorizontalActorListHolder(
                ItemActorListHorizontalBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ),
                actorClick
            )

    }

    private val adapter = HorizontalListAdapter(showMovieClick = actorClick)

}