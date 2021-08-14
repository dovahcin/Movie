package com.movie.android.utils

import com.movie.android.domain.BelongsToCollection
import com.movie.android.domain.Details
import com.movie.android.domain.Movies

data class DetailsDataModel(
    val similarities: Movies = Movies(
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
    val recommendations: Movies = Movies(
        page = -1,
        total_pages = -1,
        total_results = -1,
        results = mutableListOf()
    )
)
