package com.movie.android.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.databinding.ItemListUpcomingBinding
import com.movie.android.explore.ExploreItem.HorizontalListUpcoming

class HorizontalUpcomingHolder(
    val binding: ItemListUpcomingBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {

        fun create(parent: ViewGroup): HorizontalUpcomingHolder =
            HorizontalUpcomingHolder(
                ItemListUpcomingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
    }

    fun bind(it: HorizontalListUpcoming) {
        binding.item = it
        binding.executePendingBindings()
    }


}