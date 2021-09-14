package com.movie.android.presentation.features.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.databinding.ItemSearchHistoryBinding
import com.movie.android.domain.SearchHistory

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