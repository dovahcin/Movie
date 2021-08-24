package com.movie.android.presentation.features.explore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.databinding.ItemMovieListHorizontalBinding
import com.movie.android.domain.ExploreItem.HorizontalMovieList
import com.movie.android.domain.Movie

class HorizontalMovieListHolder(
    val binding: ItemMovieListHorizontalBinding,
    movieClick: (Movie) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup, movieClick: (Movie) -> Unit): HorizontalMovieListHolder =
            HorizontalMovieListHolder(
                ItemMovieListHorizontalBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false),
                movieClick
            )
    }

    private val adapter = HorizontalListAdapter(showMovieClick = movieClick);

    init {
      binding.horizontalRecyclerView.adapter = adapter
    }

    fun bind(it: HorizontalMovieList) {
        binding.item = it
        adapter.update(it.movies.toMutableList())
        binding.executePendingBindings()
    }


}