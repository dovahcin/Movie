package com.ilkinyazar.actordetails.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.movie.android.domain.Movie
import com.movie.android.domain.Movie.MovieViewType.SHOW_MORE

class HorizontalActorMovieAdapter(
    private val itemClick: (Int) -> Unit,
    private val showMoreClick: (Int) -> Unit,
    private var listId: Int = -1,
    private var items: MutableList<Movie> = mutableListOf(),
) : RecyclerView.Adapter<ViewHolder>() {


    private val SHOW_MORE_TYPE = 2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        if (viewType == SHOW_MORE_TYPE) ShowMoreHolder.create(parent)
        else HorizontalActorMovieHolder.create(parent)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        if (holder is HorizontalActorMovieHolder) {
            holder.bind(item)
            holder.itemView.setOnClickListener { itemClick.invoke(item.id) }
        } else {
            holder.itemView.setOnClickListener { showMoreClick.invoke(listId) }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].type == SHOW_MORE) SHOW_MORE_TYPE else 0
    }

    override fun getItemCount(): Int = items.size

    fun update(images: List<Movie>, listId: Int) {
        this.listId = listId
        this.items.clear()
        this.items.addAll(images)
        notifyItemRangeInserted(itemCount, images.size)
    }

}