package com.movie.android.presentation.features.explore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.databinding.ItemActorListHorizontalBinding
import com.movie.android.domain.Actor
import com.movie.android.domain.ExploreItem

class HorizontalActorListHolder(
    val binding: ItemActorListHorizontalBinding,
    actorClick: (Actor) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup, actorClick: (Actor) -> Unit) =
            HorizontalActorListHolder(
                ItemActorListHorizontalBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ),
                actorClick
            )
    }

    private val adapter = HorizontalActorAdapter(actorClick)

    init {
        binding.actorsListRecyclerView.adapter = adapter
    }

    fun bind(it: ExploreItem.Artists) {
        binding.item = it
        adapter.update(it.actors.toMutableList())
        binding.executePendingBindings()
    }
}