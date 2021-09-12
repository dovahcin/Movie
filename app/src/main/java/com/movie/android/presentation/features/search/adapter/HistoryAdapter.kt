package com.movie.android.presentation.features.search.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.domain.History

class HistoryAdapter(
    private val crossClick: (Int) -> Unit,
    private val textClick: (String) -> Unit,
    private var items: MutableList<History> = mutableListOf()
) : RecyclerView.Adapter<HistoryHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder =
        HistoryHolder.create(parent)

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        holder.bind(items[position])
        holder.binding.cross.setOnClickListener {
            crossClick.invoke(items[position].id!!)
        }
        holder.binding.textName.setOnClickListener {
            textClick.invoke(holder.binding.textName.text.toString())
        }
    }

    override fun getItemCount(): Int = items.size

    fun update(history: MutableList<History>) {
        val currentSize = items.size
        this.items.clear()
        this.items.addAll(history)
        notifyItemRangeRemoved(0, currentSize)
        notifyItemRangeInserted(itemCount, items.size)
    }
}