package com.movie.android.domain.details.similar

data class Similarities(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)