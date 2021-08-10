package com.movie.android.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.databinding.ItemMovieBinding
import com.movie.android.domain.Movie
import com.movie.android.utils.loadImage
import com.movie.android.utils.screenWidth
import kotlin.math.roundToInt

class MovieViewHolder(private val binding: ItemMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): MovieViewHolder =
            MovieViewHolder(
                ItemMovieBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }

    init {
        binding.imageView.layoutParams.width = (screenWidth() * 0.4).roundToInt()
    }

    val imageView = binding.imageView

    fun bind(movie: Movie) {
        binding.imageView.loadImage(movie.posterPath)
        binding.result = movie
        binding.executePendingBindings()
    }
}