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
            Type.HorizontalList.ordinal -> PopularImageViewHolder.create(parent)
            Type.VerticalList.ordinal -> UpcomingImageViewHolder.create(parent)
            Type.Promotions.ordinal -> UpcomingImageViewHolder.create(parent)
            Type.Artists.ordinal -> UpcomingImageViewHolder.create(parent)
            else -> EmptyHolder.create(parent)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exploreItem = items[position]

        when(exploreItem){
            is HorizontalList -> {
                for (i in 1..exploreItem.movies.size - 1 ) {
                    (holder as PopularImageViewHolder).bind(exploreItem.movies[i])
                }
            }
            is Artists -> TODO()
            is Promotions -> TODO()
            is VerticalList -> (holder as UpcomingImageViewHolder).bind(exploreItem.movies[position])
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