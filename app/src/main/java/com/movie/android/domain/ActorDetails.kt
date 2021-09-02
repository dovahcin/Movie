package com.movie.android.domain

data class ActorsDataModel(
    val actorDetails: ActorDetails = ActorDetails(),
    val actors: List<Actor> = mutableListOf(),
    val actorMovies: MovieList = MovieList()
)

data class ActorDetails(
    val adult: Boolean = false,
    val also_known_as: List<Any> = listOf(),
    var biography: String = "",
    val birthday: String = "",
    val deathday: Any? = "",
    val gender: Int = -1,
    val homepage: Any = "",
    val id: Int = -1,
    val imdb_id: String = "",
    val known_for_department: String = "",
    val name: String = "",
    val place_of_birth: String = "",
    val popularity: Double = -1.0,
    val profile_path: String = ""
){
    fun expireDate(): String {
        return deathday?.toString() ?: "-"
    }

    val profilePath
    get() = "https://image.tmdb.org/t/p/w600_and_h900_bestv2${profile_path}"
}