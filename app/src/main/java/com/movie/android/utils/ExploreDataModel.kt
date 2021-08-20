package com.movie.android.utils

import com.movie.android.domain.Dates
import com.movie.android.domain.Movies
import com.movie.android.domain.Upcomings

data class ExploreDataModel(
    val upcomings: Upcomings = Upcomings(
        dates = Dates(
            "", ""
        ),
        page = -1,
        results = mutableListOf(),
        total_pages = -1,
        total_results = -1
    ),
    val populars: Movies = Movies(
        page = -1,
        total_pages = -1,
        total_results = -1,
        results = mutableListOf()
    )
)
