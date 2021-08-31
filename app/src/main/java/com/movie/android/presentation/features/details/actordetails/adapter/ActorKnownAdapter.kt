package com.movie.android.presentation.features.details.actordetails.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.domain.KnownFor
import com.movie.android.domain.Movie

class ActorKnownAdapter(
    private val items: MutableList<Movie> = mutableListOf()
) : RecyclerView.Adapter<ActorKnownHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorKnownHolder =
        ActorKnownHolder.create(parent)


    override fun onBindViewHolder(holder: ActorKnownHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun update(items: MutableList<Movie>) {
        this.items.clear()
        this.items.addAll(items)
        notifyItemRangeInserted(itemCount, items.size)
    }
}