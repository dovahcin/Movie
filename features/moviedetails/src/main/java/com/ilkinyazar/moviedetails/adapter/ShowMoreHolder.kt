package com.ilkinyazar.moviedetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilkinyazar.moviedetails.R
import com.ilkinyazar.moviedetails.databinding.ItemActorMovieHorizontalBinding

class ShowMoreHolder(
    binding: ItemActorMovieHorizontalBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) =

            ShowMoreHolder(
                ItemActorMovieHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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