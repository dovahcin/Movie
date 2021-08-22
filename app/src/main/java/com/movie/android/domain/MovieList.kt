package com.movie.android.domain

data class MovieList(
    val page: Int,
    var results: MutableList<Movie>,
    val total_pages: Int,
    val total_results: Int
)