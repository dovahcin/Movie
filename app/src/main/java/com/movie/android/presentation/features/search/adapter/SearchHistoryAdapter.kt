package com.movie.android.presentation.features.search.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.domain.SearchHistory

class SearchHistoryAdapter(
    private val onBackClick: (Int) -> Unit,
    private val onHistoryClick: (String) -> Unit,
    private var items: MutableList<SearchHistory> = mutableListOf()
) : RecyclerView.Adapter<SearchHistoryHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryHolder =
        SearchHistoryHolder.create(parent)

    override fun onBindViewHolder(holderSearch: SearchHistoryHolder, position: Int) {
        holderSearch.bind(items[position])
        holderSearch.binding.clearHistoryImageView.setOnClickListener {
            onBackClick.invoke(items[position].id!!)
        }
        holderSearch.binding.textName.setOnClickListener {
            onHistoryClick.invoke(holderSearch.binding.textName.text.toString())
        }
    }

    override fun getItemCount(): Int = items.size

    fun update(history: MutableList<SearchHistory>) {
        this.items.clear()
        this.items.addAll(history)
        notifyItemRangeInserted(0, itemCount)
    }
}