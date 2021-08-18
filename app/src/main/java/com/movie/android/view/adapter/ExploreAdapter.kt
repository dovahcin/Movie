package com.movie.android.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.movie.android.domain.explore.ExploreItem
import com.movie.android.domain.explore.ExploreItem.Artists
import com.movie.android.domain.explore.ExploreItem.HorizontalList
import com.movie.android.domain.explore.ExploreItem.Promotions
import com.movie.android.domain.explore.ExploreItem.Type
import com.movie.android.domain.explore.ExploreItem.VerticalList

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
            is HorizontalList-> TODO()
            is Artists -> TODO()
            is Promotions -> TODO()
            is VerticalList -> TODO()
        }

    }

    override fun getItemViewType(position: Int) = items[position].viewType.ordinal

    override fun getItemCount(): Int = items.size

    fun update(movie: MutableList<ExploreItem>) {
        this.items.clear()
        this.items.addAll(movie)
        notifyItemRangeInserted(itemCount, movie.size)
    }
}