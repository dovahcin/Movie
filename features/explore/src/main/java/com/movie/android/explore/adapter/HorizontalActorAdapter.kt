package com.movie.android.explore.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.domain.Actor

class HorizontalActorAdapter(
    private val showActorClick: (Actor) -> Unit,
    private val items: MutableList<Actor> = mutableListOf()
) : RecyclerView.Adapter<HorizontalActorHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalActorHolder =
        HorizontalActorHolder.create(parent)

    override fun onBindViewHolder(holder: HorizontalActorHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener { showActorClick.invoke(items[position]) }
    }

    override fun getItemCount(): Int = items.size

    fun update(it: MutableList<Actor>) {
        this.items.clear()
        this.items.addAll(it)
        notifyItemRangeInserted(itemCount, items.size)
    }
}