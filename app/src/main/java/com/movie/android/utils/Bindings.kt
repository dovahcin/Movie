package com.movie.android.utils

import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.movie.android.domain.Movie
import com.movie.android.view.adapter.HorizontalMovieHolder

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
