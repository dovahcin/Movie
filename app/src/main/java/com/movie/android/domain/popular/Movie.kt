package com.movie.android.domain.popular

data class Movie(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) {

    val posterPath
    get() = "https://image.tmdb.org/t/p/w600_and_h900_bestv2$poster_path"

    val vote
    get() = vote_average.toString()
}