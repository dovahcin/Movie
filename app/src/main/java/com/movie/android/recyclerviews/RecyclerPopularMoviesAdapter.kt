package com.movie.android.recyclerviews

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.databinding.ItemMovieBinding
import com.movie.android.datamodel.Result
import com.movie.android.picasso.PicassoService

class RecyclerPopularMoviesAdapter(
    private var result: List<Result>,
    private val picassoServices: PicassoService
) :
    RecyclerView.Adapter<RecyclerPopularMoviesAdapter.PopularMoviesViewHolder>() {

    private val TAG = "Recycler"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        return PopularMoviesViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {

        val uri = "https://image.tmdb.org/t/p/w600_and_h900_bestv2${result[position].poster_path}"

        Log.d(TAG, "result : $uri")

        holder.bind(result[position])

        picassoServices.initPicasso(uri, holder.imageView)

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

    fun reloadData(result: List<Result>) {
        this.result = result
    }
}