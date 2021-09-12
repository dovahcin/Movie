package com.movie.android.presentation.features.search.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.domain.Movie

class SearchResultAdapter(
    private val itemClick: (String, Int) -> Unit,
    private var searchQuery: String,
    var items: MutableList<Movie> = mutableListOf(),
) : RecyclerView.Adapter<SearchResultHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultHolder =
        SearchResultHolder.create(parent)

    override fun onBindViewHolder(holder: SearchResultHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            itemClick.invoke(searchQuery, items[position].id)
        }
    }

    override fun getItemCount(): Int = items.size

    fun update(items: MutableList<Movie>) {
        val currentSize = this.items.size
        this.items.clear()
        this.items.addAll(items)
        notifyItemRangeRemoved(0, currentSize)
        notifyItemRangeInserted(itemCount, items.size)
    }

    fun updateText(searchQuery: String) {
        this.searchQuery = searchQuery
    }
}