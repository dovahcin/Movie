package com.movie.android.presentation.features.explore.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.movie.android.domain.Movie
import com.movie.android.presentation.features.details.adapter.HorizontalMovieHolder

class HorizontalMovieAdapter(
    private val showMovieClick: (Movie) -> Unit,
    private var items: MutableList<Movie> = mutableListOf()
) : Adapter<HorizontalMovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalMovieHolder =
        HorizontalMovieHolder.create(parent)

    override fun onBindViewHolder(holder: HorizontalMovieHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener { showMovieClick.invoke(items[position]) }
    }

    override fun getItemCount(): Int = items.size

    fun update(it: MutableList<Movie>) {
        this.items.clear()
        this.items.addAll(it)
        notifyItemRangeInserted(itemCount, items.size)

    }
}
