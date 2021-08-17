package com.movie.android.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.movie.android.domain.Movie

class ExploreAdapter(
    private var items: MutableList<Movie> = mutableListOf()
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            1 -> PopularImageViewHolder.create(parent)
            2 -> UpcomingImageViewHolder.create(parent)
            else -> EmptyHolder.create(parent)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder) {
            is PopularImageViewHolder -> {
                holder.bind(items[position])
            }
            is UpcomingImageViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position].type) {

            Movie.MovieViewType.HORIZONTAL_POPULAR -> 1
            Movie.MovieViewType.HORIZONTAL_UPCOMING -> 2

            else -> -1

        }

    }

    override fun getItemCount(): Int = items.size
}