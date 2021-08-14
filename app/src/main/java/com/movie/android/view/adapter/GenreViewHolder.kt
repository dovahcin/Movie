package com.movie.android.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.databinding.ItemGenreDetailBinding
import com.movie.android.domain.details.Genre

class GenreViewHolder(
    private val binding: ItemGenreDetailBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) =

            GenreViewHolder(
                ItemGenreDetailBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

    }

    fun bind(genre: Genre) {
        binding.genre = genre
        binding.executePendingBindings()
    }


}