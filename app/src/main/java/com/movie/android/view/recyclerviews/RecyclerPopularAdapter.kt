package com.movie.android.view.recyclerviews

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.databinding.ItemMovieBinding
import com.movie.android.datamodel.Result
import com.movie.android.picasso.loadImage

class RecyclerPopularAdapter(
    private var result: List<Result>
) :
    RecyclerView.Adapter<RecyclerPopularAdapter.PopularMoviesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        return PopularMoviesViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {

        holder.bind(result[position])

        Log.d("ImageView", "${result[position].posterPath}")

        holder.imageView.loadImage(result[position].posterPath)

    }

    override fun getItemCount(): Int = result.size

    inner class PopularMoviesViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        val imageView = binding.imageView

        fun bind(result: Result) {

            binding.result = result
            binding.executePendingBindings()
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun reloadData(result: List<Result>) {
        this.result = result
        notifyDataSetChanged()
    }
}