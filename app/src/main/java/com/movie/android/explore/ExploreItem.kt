package com.movie.android.explore

import com.movie.android.domain.Movie

data class Explore(val items: MutableList<ExploreItem> = mutableListOf())
sealed class ExploreItem(val viewType : Type){
  data class HorizontalList(val movies: List<Movie>) : ExploreItem(Type.HorizontalList)
  data class VerticalList(val movies: List<Movie>) : ExploreItem(Type.VerticalList)
  data class Artists(val imageUrls: List<String>) : ExploreItem(Type.Artists)
  data class Promotions(val movies: List<Movie>) : ExploreItem(Type.Promotions)

  enum class Type{HorizontalList,VerticalList,Artists,Promotions}

}
