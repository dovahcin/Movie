package com.movie.android.domain

data class DetailsDataModel(
    val similarities: MovieList = MovieList(
        page = -1,
        total_pages = -1,
        total_results = -1,
        results = mutableListOf()
    ),
    val details: Details = Details(
        adult = false,
        backdrop_path = "",
        belongs_to_collection = BelongsToCollection("", -1, "", ""),
        budget = -1,
        genres = listOf(),
        homepage = "",
        id = -1,
        imdb_id = "",
        original_language = "",
        original_title = "",
        overview = "",
        popularity = -1.0,
        poster_path = "",
        production_companies = listOf(),
        production_countries = listOf(),
        release_date = "",
        revenue = -1,
        runtime = -1,
        spoken_languages = listOf(),
        status = "",
        tagline = "",
        title = "",
        video = false,
        vote_average = -1.0,
        vote_count = -1
    ),
    val recommendations: MovieList = MovieList(
        page = -1,
        total_pages = -1,
        total_results = -1,
        results = mutableListOf()
    )
)


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

data class SpokenLanguage(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)

data class Genre(
    val id: Int,
    val name: String
)

data class BelongsToCollection(
    val backdrop_path: String,
    val id: Int,
    val name: String,
    val poster_path: String
)

data class ProductionCompany(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
)

data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
)