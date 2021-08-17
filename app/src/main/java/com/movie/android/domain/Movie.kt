package com.movie.android.domain

import com.movie.android.domain.Movie.MovieViewType.*

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
    val type: MovieViewType
) {

    val posterPath
    get() = "https://image.tmdb.org/t/p/w600_and_h900_bestv2$poster_path"

    val vote
    get() = vote_average.toString()

    companion object{
        fun createShowMore(): Movie {
            return Movie(false,"", emptyList(),-1,"","","",0.0,"","","",false,0.0,0,SHOW_MORE)
        }
         fun createUpcoming(movies: MutableList<Movie>): Movie {
             lateinit var movie: Movie
             movies.forEach{
                 movie = Movie(
                     it.adult,
                     it.backdrop_path,
                     it.genre_ids,
                     it.id,
                     it.original_language,
                     it.original_title,
                     it.overview,
                     it.popularity,
                     it.poster_path,
                     it.release_date,
                     it.title,
                     it.video,
                     it.vote_average,
                     it.vote_count,
                     type = HORIZONTAL_UPCOMING
                 )
             }
             return movie
        }
        fun createPopular(movies: MutableList<Movie>): Movie {
             lateinit var movie: Movie
             movies.forEach{
                 movie = Movie(
                     it.adult,
                     it.backdrop_path,
                     it.genre_ids,
                     it.id,
                     it.original_language,
                     it.original_title,
                     it.overview,
                     it.popularity,
                     it.poster_path,
                     it.release_date,
                     it.title,
                     it.video,
                     it.vote_average,
                     it.vote_count,
                     type = HORIZONTAL_POPULAR
                 )
             }
             return movie
        }


    }
    enum class MovieViewType{
        HORIZONTAL_DETAIL, SHOW_MORE,
        HORIZONTAL_UPCOMING, HORIZONTAL_POPULAR, TITLE_POPULAR, TITLE_UPCOMING,VIEWPAGER
    }
}