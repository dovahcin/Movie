package com.movie.android.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.domain.details.Genre

class GenreAdapter(
    private var genres: MutableList<Genre>
) : RecyclerView.Adapter<GenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder =
        GenreViewHolder.create(parent)


    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(genres[position])
    }

    override fun getItemCount(): Int = genres.size

    fun update(genres: MutableList<Genre>) {
        this.genres = genres
        notifyItemRangeInserted(itemCount, genres.size)
    }
}