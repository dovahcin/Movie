package com.movie.android.domain

import kotlinx.parcelize.RawValue

data class SearchDataModel(
    val movies: MovieList = MovieList(),
    val histories: MutableList<SearchHistory> = mutableListOf()
) {

    fun reduce(it: MutableList<SearchHistory>): @RawValue SearchDataModel {
        histories.clear()
        histories.addAll(it)
        return this
    }
}

val movieListInitialValue = MovieList()
data class MovieList(
    var id: Int = 0,
    val page: Int = 0,
    var results: MutableList<Movie> = mutableListOf(),
    val total_pages: Int = 0,
    val total_results: Int = 0
) {
    fun isInitialized() =
        this == movieListInitialValue
}

