package com.movie.android.domain

val actorsInitialValue = ActorList()
data class ActorList(
    val page: Int = -1,
    var results: MutableList<Actor> = mutableListOf(),
    val total_pages: Int = -1,
    val total_results: Int = -1
) {
    fun isInitialValue() =
        this == actorsInitialValue
}

data class KnownFor(
    val adult: Boolean,
    val backdrop_path: String,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val original_title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) {
    val backdropPath
    get() = "https://image.tmdb.org/t/p/w600_and_h900_bestv2$backdrop_path"
}