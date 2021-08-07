package com.movie.android.datamodel

data class MoviesList(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)