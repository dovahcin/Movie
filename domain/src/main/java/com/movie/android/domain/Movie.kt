package com.movie.android.domain

import com.movie.android.domain.Movie.MovieViewType.MOVIE
import com.movie.android.domain.Movie.MovieViewType.SHOW_MORE
import java.math.RoundingMode

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
    val vote_count: Int,
    val type: MovieViewType = MOVIE
) {

    val posterPath
    get() = "https://image.tmdb.org/t/p/w600_and_h900_bestv2$poster_path"

    val vote
    get() = vote_average.toBigDecimal().setScale(1, RoundingMode.HALF_UP).toString()

    companion object{
        fun createShowMore(): Movie {
            return Movie(false,"", emptyList(),-1,"","","",0.0,"","","",false,0.0,0,SHOW_MORE)
        }
    }
    enum class MovieViewType{
        SHOW_MORE, MOVIE
    }
}