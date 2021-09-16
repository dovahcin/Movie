package com.movie.android.presentation.features.search.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.domain.SearchHistory

class SearchHistoryAdapter(
    private val onDeleteHistory: (Int) -> Unit,
    private val onClickHistory: (String) -> Unit,
    private var items: MutableList<SearchHistory> = mutableListOf()
) : RecyclerView.Adapter<SearchHistoryHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryHolder =
        SearchHistoryHolder.create(parent)

    override fun onBindViewHolder(holderSearch: SearchHistoryHolder, position: Int) {
        holderSearch.bind(items[position])
        holderSearch.binding.clearHistoryImageView.setOnClickListener {
            onDeleteHistory.invoke(items[position].id!!)
        }
        holderSearch.binding.textName.setOnClickListener {
            onClickHistory.invoke(holderSearch.binding.textName.text.toString())
        }
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(history: MutableList<SearchHistory>) {
        this.items.clear()
        this.items.addAll(history)
        notifyDataSetChanged()
    }
}