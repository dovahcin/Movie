package com.movie.android.presentation.features.explore.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.movie.android.domain.Movie

class HorizontalMovieAdapter(
    private val showMovieClick: (Movie) -> Unit,
    private var items: MutableList<Movie> = mutableListOf()
) : Adapter<com.ilkinyazar.moviedetails.adapter.HorizontalActorMovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): com.ilkinyazar.moviedetails.adapter.HorizontalActorMovieHolder =
        com.ilkinyazar.moviedetails.adapter.HorizontalActorMovieHolder.create(parent)

    override fun onBindViewHolder(holder: com.ilkinyazar.moviedetails.adapter.HorizontalActorMovieHolder, position: Int) {
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
