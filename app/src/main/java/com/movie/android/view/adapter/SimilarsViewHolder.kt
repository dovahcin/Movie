package com.movie.android.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.databinding.ItemSimilarBinding
import com.movie.android.domain.details.similar.Result
import com.movie.android.utils.loadImage
import com.movie.android.utils.reduceWidthBy

class SimilarsViewHolder(
    private val binding: ItemSimilarBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) =
            SimilarsViewHolder(
                ItemSimilarBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }

    val imageView = binding.horizontalImageView

    init {
        imageView reduceWidthBy 0.4f
    }

    fun bind(similarity: Result) {
        imageView.loadImage(similarity.posterPath)
        binding.similars = similarity
        binding.executePendingBindings()
    }

}