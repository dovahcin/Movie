package com.movie.android.domain

data class ActorMovies(
    val page: Int = -1,
    var results: List<Movie> = mutableListOf(),
    val total_pages: Int = -1,
    val total_results: Int = -1
)