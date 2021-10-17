package com.movie.android.explore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.explore.databinding.ItemActorHorizontalBinding
import com.movie.android.explore.loadImage
import com.movie.android.domain.Actor

class HorizontalActorHolder(
    val binding: ItemActorHorizontalBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) =
            HorizontalActorHolder(
                ItemActorHorizontalBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }

    val imageView = binding.actorsImageView

    fun bind(it: Actor) {
        binding.actor = it
        imageView.loadImage(it.profilePath)
        binding.executePendingBindings()
    }

}