package com.movie.android.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.domain.SearchHistory
import com.movie.android.search.databinding.ItemSearchHistoryBinding

class SearchHistoryHolder(val binding: ItemSearchHistoryBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) =
            SearchHistoryHolder(
                ItemSearchHistoryBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }

    fun bind(history: SearchHistory) {
        binding.movie = history
        binding.executePendingBindings()
    }

}