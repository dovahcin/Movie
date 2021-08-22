package com.movie.android.features.popular.explore

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.movie.android.features.popular.explore.ExploreItem.*
import com.movie.android.view.adapter.EmptyHolder

class ExploreAdapter(
    private val navigateToPop: () -> Unit,
    private val navigateToUp: () -> Unit,
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
            is HorizontalListPopular -> {
                (holder as HorizontalPopularHolder).bind(exploreItem)
                holder.binding.PopularShowAll.setOnClickListener { navigateToPop.invoke() }
            }
            is HorizontalListUpcoming -> {
                (holder as HorizontalUpcomingHolder).bind(exploreItem)
                holder.binding.upcomingShowAll.setOnClickListener { navigateToUp.invoke() }
            }
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