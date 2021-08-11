package com.movie.android.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.domain.Movie

class PopularMovieAdapter(
    private var items: MutableList<Movie>, private val itemClick : (Int)->Unit
) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder.create(parent)

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = items[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { itemClick.invoke(movie.id) }
    }

    override fun getItemCount() = items.size

    fun update(movie: MutableList<Movie>) {
        val oldSize = itemCount
        this.items.addAll(movie)
        notifyItemRangeInserted(oldSize, movie.size)
    }

}