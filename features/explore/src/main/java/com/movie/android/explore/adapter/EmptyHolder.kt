package com.movie.android.explore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.explore.databinding.ItemEmptyBinding

class EmptyHolder(val binding: ItemEmptyBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup): EmptyHolder {
            return EmptyHolder(
                ItemEmptyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

}