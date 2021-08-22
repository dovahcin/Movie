package com.movie.android.explore

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.movie.android.explore.ExploreItem.*
import com.movie.android.view.adapter.EmptyHolder

class ExploreAdapter(
    private var items: MutableList<ExploreItem> = mutableListOf()
) : RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            Type.HorizontalListPopular.ordinal -> HorizontalPopularHolder.create(parent)
            Type.HorizontalListUpcoming.ordinal -> HorizontalUpcomingHolder.create(parent)
            Type.Promotions.ordinal -> HorizontalUpcomingHolder.create(parent)
            Type.Artists.ordinal -> HorizontalUpcomingHolder.create(parent)
            else -> EmptyHolder.create(parent)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        when(val exploreItem = items[position]){
            is HorizontalListPopular -> (holder as HorizontalPopularHolder).bind(exploreItem)
            is HorizontalListUpcoming -> (holder as HorizontalUpcomingHolder).bind(exploreItem)
            is Artists -> TODO()
            is Promotions -> TODO()
        }

    }

    override fun getItemViewType(position: Int) = items[position].viewType.ordinal

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(item: MutableList<ExploreItem>) {
        this.items.clear()
        this.items.addAll(item)
        notifyItemRangeInserted(itemCount, item.size)
    }
}