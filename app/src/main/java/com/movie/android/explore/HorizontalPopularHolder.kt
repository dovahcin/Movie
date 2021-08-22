package com.movie.android.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.databinding.ItemListPopularBinding
import com.movie.android.explore.ExploreItem.HorizontalList

class HorizontalPopularHolder(
    val binding: ItemListPopularBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {

        fun create(parent: ViewGroup): HorizontalPopularHolder =
            HorizontalPopularHolder(
                ItemListPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
    }

    fun bind(it: HorizontalList) {
        binding.item = it
        binding.executePendingBindings()
    }


}