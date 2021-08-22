package com.movie.android.presentation.features.details.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.movie.android.domain.Movie
import com.movie.android.domain.Movie.MovieViewType.SHOW_MORE

class HorizontalMovieAdapter(
    private var items: MutableList<Movie> = mutableListOf(),
    private val itemClick : (Int)->Unit,
    private val showMoreClick : (Int)->Unit,
) : RecyclerView.Adapter<ViewHolder>() {

    private val SHOW_MORE_TYPE = 2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        if (viewType == SHOW_MORE_TYPE) ShowMoreHolder.create(parent)
    else HorizontalMovieHolder.create(parent)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        if (holder is HorizontalMovieHolder){
            holder.bind(item)
            holder.itemView.setOnClickListener { itemClick.invoke(item.id) }
        }else{
            holder.itemView.setOnClickListener { showMoreClick.invoke(item.id) }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].type == SHOW_MORE) SHOW_MORE_TYPE else 0
    }
    override fun getItemCount(): Int = items.size

    fun update(images: List<Movie>) {
        this.items.clear()
        this.items.addAll(images)
        notifyItemRangeInserted(itemCount, images.size)
    }

}