package com.movie.android.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.databinding.ItemUpcomingImageBinding
import com.movie.android.domain.Movie
import com.movie.android.utils.loadImage
import com.movie.android.utils.reduceWidthBy

class UpcomingImageViewHolder(
    val binding: ItemUpcomingImageBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {

        fun create(parent: ViewGroup): UpcomingImageViewHolder =
            UpcomingImageViewHolder(
                ItemUpcomingImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
    }

    init {
        binding.upcomingImages reduceWidthBy 0.4f
    }

    fun bind(image: Movie) {
        binding.upcomingImages.loadImage(image.posterPath)
        binding.executePendingBindings()
    }


}