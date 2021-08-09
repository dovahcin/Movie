package com.movie.android.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.domain.Movie

class PopularMovieAdapter(private var movie: MutableList<Movie>) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder.create(parent)

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movie[position])
    }

    override fun getItemCount(): Int = movie.size

    fun update(movie: MutableList<Movie>) {
        val oldSize = itemCount
        this.movie.addAll(movie)
        notifyItemRangeInserted(oldSize, movie.size)
    }

}