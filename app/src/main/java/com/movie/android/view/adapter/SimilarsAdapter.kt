package com.movie.android.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.domain.details.similar.Result

class SimilarsAdapter(
    private var similars: MutableList<Result>,
) : RecyclerView.Adapter<SimilarsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarsViewHolder =
        SimilarsViewHolder.create(parent)


    override fun onBindViewHolder(holder: SimilarsViewHolder, position: Int) {
        holder.bind(similars[position])
    }

    override fun getItemCount(): Int = similars.size

    fun update(similars: MutableList<Result>) {
        this.similars = similars
        notifyItemRangeInserted(itemCount, similars.size)
    }
}