package com.movie.android.presentation.features.actorlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.databinding.ItemActorVerticalBinding
import com.movie.android.domain.Actor
import com.movie.android.presentation.utils.loadImage
import com.movie.android.presentation.utils.reduceWidthBy

class VerticalActorHolder(private val binding: ItemActorVerticalBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) =
            VerticalActorHolder(
                ItemActorVerticalBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }

    init {
        binding.imageView reduceWidthBy 0.3f
    }

    fun bind(actor: Actor) {
        binding.actor = actor
        binding.imageView.loadImage(actor.profilePath)
        binding.executePendingBindings()
    }

}