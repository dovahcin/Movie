package com.movie.android.features.popular.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.databinding.ItemListPopularBinding
import com.movie.android.features.popular.explore.ExploreItem.HorizontalListPopular

class HorizontalPopularHolder(
    val binding: ItemListPopularBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {

        fun create(parent: ViewGroup): HorizontalPopularHolder =
            HorizontalPopularHolder(
                ItemListPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
    }

    fun bind(it: HorizontalListPopular) {
        binding.item = it
        binding.executePendingBindings()
    }


}