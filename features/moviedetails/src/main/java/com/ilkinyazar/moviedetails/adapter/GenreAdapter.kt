package com.ilkinyazar.moviedetails.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.domain.Genre

class GenreAdapter(
    private var genres: MutableList<Genre> = mutableListOf()
) : RecyclerView.Adapter<GenreHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreHolder =
        GenreHolder.create(parent)


    override fun onBindViewHolder(holder: GenreHolder, position: Int) {
        holder.bind(genres[position])
    }

    override fun getItemCount(): Int = genres.size

    fun update(genres: MutableList<Genre>) {
        this.genres = genres
        notifyItemRangeInserted(itemCount, genres.size)
    }
}