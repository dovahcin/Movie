package com.movie.android.domain

import com.movie.android.domain.ExploreItem.Type.Artists
import com.movie.android.domain.ExploreItem.Type.VerticalMovieList
import com.movie.android.domain.ExploreItem.Type.HorizontalMovieList
import com.movie.android.domain.ExploreItem.Type.Promotions

data class Explore(val items: MutableList<ExploreItem> = mutableListOf())
sealed class ExploreItem(val viewType : Type){

  data class VerticalMovieList(
    val title: String, val hasShowAll: Boolean, val movies: List<Movie>
  ) : ExploreItem(VerticalMovieList)

  data class HorizontalMovieList(
    val title: String, val hasShowAll: Boolean, val movies: List<Movie>
  ) : ExploreItem(HorizontalMovieList)

  data class Artists(val imageUrls: List<String>) : ExploreItem(Artists)
  data class Promotions(val movies: List<Movie>) : ExploreItem(Promotions)

  enum class Type{VerticalMovieList,HorizontalMovieList,Artists,Promotions}

}