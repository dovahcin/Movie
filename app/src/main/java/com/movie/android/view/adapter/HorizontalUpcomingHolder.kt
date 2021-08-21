package com.movie.android.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.databinding.ItemListUpcomingBinding
import com.movie.android.domain.explore.ExploreItem.VerticalList

class HorizontalUpcomingHolder(
    val binding: ItemListUpcomingBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {

        fun create(parent: ViewGroup): HorizontalUpcomingHolder =
            HorizontalUpcomingHolder(
                ItemListUpcomingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
    }

    fun bind(it: VerticalList) {
        binding.item = it
        binding.executePendingBindings()
    }


}