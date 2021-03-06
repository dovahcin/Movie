package com.movie.android.explore.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.movie.android.domain.Actor
import com.movie.android.domain.ExploreItem
import com.movie.android.domain.ExploreItem.*
import com.movie.android.domain.Movie

class ExploreAdapter(
    private val showAllMoviesClick: (Type) -> Unit,
    private val showAllActorsClick: () -> Unit,
    private val actorClick: (Actor) -> Unit,
    private val movieClick: (Movie) -> Unit,
    private var items: MutableList<ExploreItem> = mutableListOf()
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            Type.VerticalMovieList.ordinal -> VerticalMovieListHolder.create(parent,movieClick)
            Type.HorizontalMovieList.ordinal -> HorizontalMovieListHolder.create(parent,movieClick)
            Type.Promotions.ordinal -> TODO()
            Type.Artists.ordinal -> HorizontalActorListHolder.create(parent, actorClick)
            else -> EmptyHolder.create(parent)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        when(val exploreItem = items[position]){
            is VerticalMovieList -> {
                (holder as VerticalMovieListHolder).bind(exploreItem)
                holder.binding.showAll.setOnClickListener {
                    showAllMoviesClick.invoke(exploreItem.viewType)
                }
            }
            is HorizontalMovieList -> {
                (holder as HorizontalMovieListHolder).bind(exploreItem)
                holder.binding.showAll.setOnClickListener {
                    showAllMoviesClick.invoke(exploreItem.viewType)
                }
            }
            is Artists -> {
                (holder as HorizontalActorListHolder).bind(exploreItem)
                holder.binding.showAll.setOnClickListener {
                    showAllActorsClick.invoke()
                }
            }
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