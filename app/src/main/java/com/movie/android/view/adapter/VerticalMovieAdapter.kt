package com.movie.android.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.domain.Movie

class VerticalMovieAdapter(
    private var items: MutableList<Movie> = mutableListOf(),
    private val itemClick : (Int)->Unit)
    : RecyclerView.Adapter<VerticalMovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VerticalMovieHolder.create(parent)

    override fun onBindViewHolder(holder: VerticalMovieHolder, position: Int) {
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