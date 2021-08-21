package com.movie.android.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.movie.android.domain.explore.ExploreItem
import com.movie.android.domain.explore.ExploreItem.*

class ExploreAdapter(
    private var items: MutableList<ExploreItem> = mutableListOf()
) : RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            Type.HorizontalList.ordinal -> HorizontalListHolder.create(parent)
            Type.VerticalList.ordinal -> UpcomingImageViewHolder.create(parent)
            Type.Promotions.ordinal -> UpcomingImageViewHolder.create(parent)
            Type.Artists.ordinal -> UpcomingImageViewHolder.create(parent)
            else -> EmptyHolder.create(parent)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        when(val exploreItem = items[position]){
            is HorizontalList -> (holder as HorizontalListHolder).bind(exploreItem)
            is Artists -> TODO()
            is Promotions -> TODO()
            is VerticalList -> {}
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