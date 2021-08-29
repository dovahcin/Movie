package com.movie.android.presentation.features.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.databinding.ItemKnownforHorizontalBinding
import com.movie.android.domain.KnownFor
import com.movie.android.presentation.utils.loadImage
import com.movie.android.presentation.utils.reduceWidthBy

class ActorKnownHolder(val binding: ItemKnownforHorizontalBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): ActorKnownHolder =
            ActorKnownHolder(
                ItemKnownforHorizontalBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

    }

    val imageView = binding.horizontalImageView

    init {
        imageView reduceWidthBy 0.4f
    }

    fun bind(it: KnownFor) {
        binding.movie = it
        imageView.loadImage(it.backdropPath)
        binding.executePendingBindings()
    }

}