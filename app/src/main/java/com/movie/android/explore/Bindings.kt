package com.movie.android.explore

import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.movie.android.details.HorizontalMovieHolder
import com.movie.android.domain.Movie

@BindingAdapter("setItems")
fun setItems(recyclerView: RecyclerView, movies: List<Movie>) {
  recyclerView.adapter = HorizontalListAdapter(movies)
}

class HorizontalListAdapter(private val items: List<Movie>) : Adapter<HorizontalMovieHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    HorizontalMovieHolder.create(parent)

  override fun onBindViewHolder(holder: HorizontalMovieHolder, position: Int) {
    holder.bind(items[position])
  }

  override fun getItemCount() = items.size
}
