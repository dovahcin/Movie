package com.movie.android.domain

data class DetailsDataModel(
    val details: Details = Details(),
    val similarities: MovieList = MovieList(),
    val recommendations: MovieList = MovieList()
)


data class Details(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val belongs_to_collection: BelongsToCollection = BelongsToCollection(),
    val budget: Int = 0,
    val genres: List<Genre> = emptyList(),
    val homepage: String = "",
    val id: Int = 0,
    val imdb_id: String = "",
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val production_companies: List<ProductionCompany> = emptyList(),
    val production_countries: List<ProductionCountry> = emptyList(),
    val release_date: String = "",
    //Sell
    val revenue: Int = 0,
    val runtime: Int = 0,
    val spoken_languages: List<SpokenLanguage> = emptyList(),
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = 0.0,
    val vote_count: Int = 0
) {
    val backDropPath
    get() = "https://image.tmdb.org/t/p/w600_and_h900_bestv2$backdrop_path"
    val posterPath
    get() = "https://image.tmdb.org/t/p/w600_and_h900_bestv2$poster_path"
    val voteAverage
    get() = (vote_average.toFloat()) / 2
}

data class SpokenLanguage(
    val english_name: String = "en",
    val iso_639_1: String = "",
    val name: String = ""
)

data class Genre(
    val id: Int = 0,
    val name: String = ""
)

data class BelongsToCollection(
    val backdrop_path: String = "",
    val id: Int = 0,
    val name: String = "",
    val poster_path: String = ""
)

data class ProductionCompany(
    val id: Int = 0,
    val logo_path: String = "",
    val name: String = "",
    val origin_country: String = ""
)

data class ProductionCountry(
    val iso_3166_1: String = "",
    val name: String = ""
)