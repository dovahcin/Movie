package com.movie.android.presentation.features.actorlist.adapter

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.domain.Actor

class VerticalActorAdapter(
    private val actorClick: (Int) -> Unit,
    private var items: MutableList<com.movie.android.domain.Actor> = mutableListOf()
) :
    RecyclerView.Adapter<VerticalActorHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalActorHolder =
        VerticalActorHolder.create(parent)


    override fun onBindViewHolder(holder: VerticalActorHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            actorClick.invoke(items[position].id)
            Log.d("adapter", "${items[position].id}")
        }
    }

    override fun getItemCount(): Int = items.size

    fun update(actors: MutableList<com.movie.android.domain.Actor>) {
        this.items.addAll(actors)
        notifyItemRangeInserted(itemCount, items.size)
    }
}