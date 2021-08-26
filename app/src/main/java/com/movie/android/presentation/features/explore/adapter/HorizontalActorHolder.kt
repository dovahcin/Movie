package com.movie.android.presentation.features.explore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.databinding.ItemActorHorizontalBinding
import com.movie.android.presentation.utils.loadImage

class HorizontalActorHolder(val binding: ItemActorHorizontalBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) =
            HorizontalActorHolder(
                ItemActorHorizontalBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

    }

    val imageView = binding.actorsImageView
    val actorName = binding.actorName

    fun bind(it: Actor) {
        binding.actor = it
        imageView.loadImage(TODO())
        binding.executePendingBindings()
    }

}