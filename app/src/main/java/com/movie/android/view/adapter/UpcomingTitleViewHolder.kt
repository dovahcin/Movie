package com.movie.android.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.databinding.ItemUpcomingImageBinding

class UpcomingTitleViewHolder(
    val binding: ItemUpcomingImageBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): UpcomingTitleViewHolder =
            UpcomingTitleViewHolder(
                ItemUpcomingImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

    }

}