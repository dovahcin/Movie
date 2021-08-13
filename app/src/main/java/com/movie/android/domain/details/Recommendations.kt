package com.movie.android.domain.details

data class Recommendations(
    val page: Int,
    val recommendation: List<Recommendation>,
    val total_pages: Int,
    val total_results: Int
)