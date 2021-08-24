package com.movie.android.presentation.features.explore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.databinding.ItemMovieListVerticalBinding
import com.movie.android.domain.ExploreItem.VerticalMovieList
import com.movie.android.domain.Movie
import com.movie.android.presentation.features.movielist.adapter.VerticalMovieAdapter

class VerticalMovieListHolder(
    val binding: ItemMovieListVerticalBinding,
    movieClick: (Movie) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup, movieClick: (Movie) -> Unit): VerticalMovieListHolder =
            VerticalMovieListHolder(
                ItemMovieListVerticalBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false),
                movieClick
            )
    }

    private val adapter = VerticalMovieAdapter(itemClick = movieClick)
    init {
      binding.verticalRecyclerView.adapter = adapter
    }

    fun bind(it: VerticalMovieList) {
        binding.item = it
        adapter.update(it.movies.toMutableList())
        binding.executePendingBindings()
    }


}