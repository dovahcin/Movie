package com.movie.android.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.databinding.ItemMovieBinding
import com.movie.android.domain.popular.Movie
import com.movie.android.utils.loadImage
import com.movie.android.utils.reduceWidthBy
import com.movie.android.utils.screenWidth
import kotlin.math.roundToInt

class MovieViewHolder(private val binding: ItemMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) =
            MovieViewHolder(
                ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
    }

    private val imageView = binding.imageView

    init {
        imageView reduceWidthBy 0.4F
    }

    fun bind(movie: Movie) {
        imageView.loadImage(movie.posterPath)
        binding.result = movie
        binding.executePendingBindings()
    }
}