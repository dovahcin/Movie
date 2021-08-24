package com.movie.android.presentation.features.explore.adapter

import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.movie.android.presentation.features.details.adapter.HorizontalMovieHolder
import com.movie.android.domain.Movie
import com.movie.android.presentation.features.movielist.adapter.VerticalMovieAdapter

class HorizontalListAdapter(
    private val items: MutableList<Movie> = mutableListOf(),val showMovieClick: (Movie) -> Unit
) : Adapter<HorizontalMovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalMovieHolder =
        HorizontalMovieHolder.create(parent)

    override fun onBindViewHolder(holder: HorizontalMovieHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { showMovieClick.invoke(item) }
    }

    override fun getItemCount() = items.size

    fun update(movie: MutableList<Movie>) {
        val oldSize = itemCount
        this.items.addAll(movie)
        notifyItemRangeInserted(oldSize, movie.size)
    }

}
