package com.movie.android.domain.details

data class Similarities(
    val page: Int,
    val similarity: List<Similarity>,
    val total_pages: Int,
    val total_results: Int
)