package com.ilkinyazar.explore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilkinyazar.explore.databinding.ItemEmptyBinding

class EmptyHolder(val binding: ItemEmptyBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup): EmptyHolder {
            return EmptyHolder(
                ItemEmptyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

}