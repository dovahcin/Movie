package com.movie.android.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.databinding.ItemPopularImageBinding
import com.movie.android.domain.Movie
import com.movie.android.utils.loadImage
import com.movie.android.utils.reduceWidthBy

class PopularImageViewHolder(
    val binding: ItemPopularImageBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {

        fun create(parent: ViewGroup): PopularImageViewHolder =
            PopularImageViewHolder(
                ItemPopularImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
    }

    init {
        binding.popularImages reduceWidthBy 0.4f
    }

    fun bind(image: Movie) {
        binding.popularImages.loadImage(image.posterPath)
        Log.d("TAG", "Pop Poster path : ${image.posterPath}")
        binding.movie = image
        binding.executePendingBindings()
    }


}