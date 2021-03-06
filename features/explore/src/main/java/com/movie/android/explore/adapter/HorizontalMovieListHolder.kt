package com.movie.android.explore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.explore.databinding.ItemMovieListHorizontalBinding
import com.movie.android.domain.ExploreItem.HorizontalMovieList
import com.movie.android.domain.Movie

class HorizontalMovieListHolder(
    val binding: ItemMovieListHorizontalBinding,
    movieClick: (Movie) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup, movieClick: (Movie) -> Unit): HorizontalMovieListHolder =
            HorizontalMovieListHolder(
                ItemMovieListHorizontalBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false),
                movieClick
            )
    }

    private val adapter = HorizontalMovieAdapter(movieClick)

    init {
      binding.horizontalRecyclerView.adapter = adapter
    }

    fun bind(it: HorizontalMovieList) {
        binding.item = it
        adapter.update(it.movies.toMutableList())
        binding.executePendingBindings()
    }


}