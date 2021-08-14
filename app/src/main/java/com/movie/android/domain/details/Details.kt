package com.movie.android.domain.details

data class Details(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: BelongsToCollection,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    //Sell
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) {
    val backDropPath
    get() = "https://image.tmdb.org/t/p/w600_and_h900_bestv2$backdrop_path"
    val posterPath
    get() = "https://image.tmdb.org/t/p/w600_and_h900_bestv2$poster_path"
    val voteAverage
    get() = (vote_average.toFloat()) / 2
}