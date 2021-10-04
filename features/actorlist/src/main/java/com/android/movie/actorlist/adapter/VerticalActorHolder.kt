package com.android.movie.actorlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.movie.actorlist.databinding.ItemActorVerticalBinding
import com.movie.android.movielist.utils.loadImage
import com.movie.android.movielist.utils.reduceWidthBy

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

    fun bind(actor: com.movie.android.domain.Actor) {
        binding.actor = actor
        binding.imageView.loadImage(actor.profilePath)
        binding.executePendingBindings()
    }

}