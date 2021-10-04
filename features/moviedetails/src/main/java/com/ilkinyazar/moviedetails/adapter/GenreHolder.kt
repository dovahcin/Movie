package com.ilkinyazar.moviedetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilkinyazar.moviedetails.databinding.ItemGenreBinding
import com.movie.android.domain.Genre

class GenreHolder(
    private val binding: ItemGenreBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) =

            GenreHolder(
                ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

    }

    fun bind(genre: Genre) {
        binding.genre = genre
        binding.executePendingBindings()
    }


}