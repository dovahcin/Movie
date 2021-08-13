package com.movie.android.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.domain.details.Recommendation

class RecommendedAdapter(
    private val images: MutableList<Recommendation>,
) : RecyclerView.Adapter<RecommendedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedViewHolder =
        RecommendedViewHolder.create(parent)


    override fun onBindViewHolder(holder: RecommendedViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size
}