package com.movie.android.presentation.features.explore.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.*
import com.movie.android.domain.HorizontalItem
import com.movie.android.presentation.features.details.adapter.HorizontalMovieHolder
import com.movie.android.domain.Movie

class HorizontalListAdapter(
    private val items: MutableList<HorizontalItem> = mutableListOf(),
    val showMovieClick: (Movie) -> Unit,
    val showActorClick: (Actor) -> Unit
) : Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when(viewType) {
           HorizontalItem.Type.HorizontalActors.ordinal -> HorizontalActorHolder.create(parent)
           HorizontalItem.Type.HorizontalMovies.ordinal -> HorizontalMovieHolder.create(parent)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (val horizontalItem = items[position]) {
            is HorizontalItem.HorizontalMovies -> {
                horizontalItem.movieList.forEach { movie ->
                    (holder as HorizontalMovieHolder).bind(movie)
                    holder.itemView.setOnClickListener { showMovieClick.invoke(movie) }
                }
            }
            is HorizontalItem.HorizontalActors -> {
                horizontalItem.actors.forEach { actor ->
                    (holder as HorizontalActorHolder).bind(actor)
                    holder.itemView.setOnClickListener { showActorClick.invoke(actor) }
                }


            }
        }
    }

    override fun getItemViewType(position: Int): Int = items[position].viewType.ordinal

    override fun getItemCount() = items.size

    fun update(movie: MutableList<HorizontalItem>) {
        val oldSize = itemCount
        this.items.addAll(movie)
        notifyItemRangeInserted(oldSize, movie.size)
    }

}
