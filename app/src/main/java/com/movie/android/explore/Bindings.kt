package com.movie.android.explore

import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.movie.android.details.HorizontalMovieHolder
import com.movie.android.details.ShowMoreHolder
import com.movie.android.domain.Movie

@BindingAdapter("setItems")
fun setItems(recyclerView: RecyclerView, movies: List<Movie>) {
    recyclerView.adapter = HorizontalListAdapter(movies)
}

private val uselessClicker1: (Int) -> Unit = {
}

private val uselessClicker2: (Int) -> Unit = {
}

class HorizontalListAdapter(
    private val items: List<Movie>,
    private val itemClick: (Int) -> Unit = uselessClicker1,
    private val showMoreClick: (Int) -> Unit = uselessClicker2
) : Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) {
            ShowMoreHolder.create(parent)
        } else {
            HorizontalMovieHolder.create(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HorizontalMovieHolder) {
            holder.bind(items[position])
            holder.itemView.setOnClickListener {
                itemClick.invoke(items[position].id)
            }
        } else {
            holder.itemView.setOnClickListener {
                showMoreClick.invoke(items[position].id)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].type == Movie.MovieViewType.SHOW_MORE) 1 else 0
    }

    override fun getItemCount() = items.size
}
