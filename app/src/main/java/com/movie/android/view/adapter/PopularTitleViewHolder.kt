package com.movie.android.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.databinding.ItemPopularTitleBinding

class PopularTitleViewHolder(
    val binding: ItemPopularTitleBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): PopularTitleViewHolder =
            PopularTitleViewHolder(
                ItemPopularTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

    }

}