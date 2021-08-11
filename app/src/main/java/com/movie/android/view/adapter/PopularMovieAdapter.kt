package com.movie.android.view.adapter

import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.movie.android.domain.popular.Movie
import com.movie.android.view.fragment.MainFragmentDirections

class PopularMovieAdapter(
    private var movie: MutableList<Movie>
) : RecyclerView.Adapter<MovieViewHolder>(), View.OnClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder.create(parent)

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movie[position])

        holder.imageView.setOnClickListener(this)


    }

    override fun getItemCount(): Int = movie.size

    fun update(movie: MutableList<Movie>) {
        val oldSize = itemCount
        this.movie.addAll(movie)
        notifyItemRangeInserted(oldSize, movie.size)
    }

    override fun onClick(view: View) {
        val action =
            MainFragmentDirections
                .actionMainFragmentToDetailsFragment("436969")
        view.findNavController().navigate(action)
    }
}