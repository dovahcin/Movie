package com.movie.android.domain.details.recommendation

data class Recommendations(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)