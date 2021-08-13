package com.movie.android.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.domain.details.Similarity

class SimilarsAdapter(
    private val images: MutableList<Similarity>,
) : RecyclerView.Adapter<SimilarsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarsViewHolder =
        SimilarsViewHolder.create(parent)


    override fun onBindViewHolder(holder: SimilarsViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size
}