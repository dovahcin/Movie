package com.movie.android.actordetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.actordetails.R
import com.movie.android.actordetails.databinding.ItemActorShowMoreHorizontalBinding

class ShowMoreHolder(
    binding: ItemActorShowMoreHorizontalBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) =

            ShowMoreHolder(
                ItemActorShowMoreHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
    }

    init {

        val attrs = intArrayOf(R.attr.selectableItemBackground)
        val typedArray = itemView.context.obtainStyledAttributes(attrs)
        val selectableItemBackground = typedArray.getResourceId(0, 0)
        typedArray.recycle()
        itemView.setBackgroundResource(selectableItemBackground)

    }

}