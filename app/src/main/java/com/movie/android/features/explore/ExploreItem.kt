package com.movie.android.features.popular.explore

import com.movie.android.domain.Movie

data class Explore(val items: MutableList<ExploreItem> = mutableListOf())
sealed class ExploreItem(val viewType : Type){
  data class HorizontalListPopular(val title: String, val hasShowAll: Boolean, val movies: List<Movie>) : ExploreItem(Type.HorizontalListPopular)
  data class HorizontalListUpcoming(val title: String, val hasShowAll: Boolean, val movies: List<Movie>) : ExploreItem(Type.HorizontalListUpcoming)
  data class Artists(val imageUrls: List<String>) : ExploreItem(Type.Artists)
  data class Promotions(val movies: List<Movie>) : ExploreItem(Type.Promotions)

  enum class Type{HorizontalListPopular,HorizontalListUpcoming,Artists,Promotions}

}
