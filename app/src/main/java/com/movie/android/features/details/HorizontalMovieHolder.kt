package com.movie.android.features.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.databinding.ItemMovieHorizontalBinding
import com.movie.android.domain.Movie
import com.movie.android.utils.loadImage
import com.movie.android.utils.reduceWidthBy

class HorizontalMovieHolder(
    private val binding: ItemMovieHorizontalBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) =
            HorizontalMovieHolder(
                ItemMovieHorizontalBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }
    val imageView = binding.horizontalImageView
    val title = binding.title

    init {
        imageView reduceWidthBy 0.4f
    }

    fun bind(it: Movie) {
        binding.movie = it
        imageView.loadImage(it.posterPath)
        binding.executePendingBindings()
    }

}