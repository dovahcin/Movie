package com.movie.android.actordetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.actordetails.databinding.ItemActorMovieHorizontalBinding
import com.movie.android.actordetails.loadImage
import com.movie.android.actordetails.reduceWidthBy
import com.movie.android.domain.Movie

class HorizontalActorMovieHolder(
    private val binding: ItemActorMovieHorizontalBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) =
            HorizontalActorMovieHolder(
                ItemActorMovieHorizontalBinding.inflate(
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