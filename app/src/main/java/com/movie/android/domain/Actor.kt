package com.movie.android.domain

data class Actor(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val known_for: List<KnownFor>,
    val known_for_department: String,
    val name: String,
    val popularity: Double,
    val profile_path: String
) {

    val profilePath
    get() = "https://image.tmdb.org/t/p/w600_and_h900_bestv2$profile_path"

}