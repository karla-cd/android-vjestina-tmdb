package agency.five.tmdb.ui

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    var adult : Boolean,
    val backdrop_path : String,
    val genre_ids : List<Int>,
    var id : Int,
    val original_language : String,
    val original_title : String,
    val overview : String,
    val popularity : Float,
    val poster_path : String,
    val release_date : String,
    val title : String,
    val video : Boolean,
    val vote_average : Float,
    val vote_count : Int
)