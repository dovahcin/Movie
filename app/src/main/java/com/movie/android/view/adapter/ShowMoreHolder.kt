package com.movie.android.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.R
import com.movie.android.databinding.ItemShowMoreHorizontalBinding

class ShowMoreHolder(
    binding: ItemShowMoreHorizontalBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) =

            ShowMoreHolder(
                ItemShowMoreHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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