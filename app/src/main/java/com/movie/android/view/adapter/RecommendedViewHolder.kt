package com.movie.android.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.databinding.ItemHorizontalmoviefeatureBinding
import com.movie.android.domain.details.Recommendation
import com.movie.android.utils.loadImage

class RecommendedViewHolder(
    private val binding: ItemHorizontalmoviefeatureBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) =
            RecommendedViewHolder(
                ItemHorizontalmoviefeatureBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }

    val imageView = binding.horizontalImageView

    fun bind(recommendation: Recommendation) {
        imageView.loadImage(recommendation.poster_path)
        binding.executePendingBindings()
    }

}