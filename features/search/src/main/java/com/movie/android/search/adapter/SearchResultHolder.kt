package com.movie.android.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.domain.Movie
import com.movie.android.search.databinding.ItemSearchResultsBinding
import com.movie.android.search.loadImage

class SearchResultHolder(
    val binding: ItemSearchResultsBinding,
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) =
            SearchResultHolder(
                ItemSearchResultsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
    }

    fun bind(queryResult: Movie) {
        binding.queryResult = queryResult
        binding.posterPath.loadImage(queryResult.posterPath)
        binding.executePendingBindings()
    }

}