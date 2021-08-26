package com.movie.android.domain

sealed class HorizontalItem(val viewType: Type) {

    data class HorizontalMovies(val movieList: MutableList<Movie>): HorizontalItem(Type.HorizontalMovies)
    data class HorizontalActors(val actors: MutableList<Actor>) : HorizontalItem(Type.HorizontalActors)

    enum class Type {
        HorizontalMovies, HorizontalActors
    }
}