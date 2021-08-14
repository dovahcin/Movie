package com.movie.android.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.databinding.ItemRecommendationBinding
import com.movie.android.domain.details.recommendation.Result
import com.movie.android.utils.loadImage
import com.movie.android.utils.reduceWidthBy

class RecommendedViewHolder(
    private val binding: ItemRecommendationBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) =
            RecommendedViewHolder(
                ItemRecommendationBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }
    val imageView = binding.horizontalImageView

    init {
        imageView reduceWidthBy 0.4f
    }

    fun bind(recommendation: Result) {
        imageView.loadImage(recommendation.posterPath)
        binding.executePendingBindings()
    }

}