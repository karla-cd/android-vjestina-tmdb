package agency.five.tmdb.ui

import kotlinx.serialization.Serializable

@Serializable
data class MovieDetails(
    val adult : Boolean,
    val backdrop_path : String,
    val belongs_to_collection : String, //?? nez koji je ovo tip
    val budget : Long,
    val genres : List<Genre>,
    val homepage : String,
    val id : Int,
    val imdb_id : String,
    val original_language : String,
    val original_title : String,
    val overview : String,
    val popularity : Float,
    val poster_path : String,
    val production_companies : List<ProductionCompany>,
    val production_countries : List<ProductionCountry>,
    val release_date : String,
    val revenue: Long,
    val runtime : Int,
    val spoken_languages : List<SpokenLanguage>,
    val status : String,
    val tagline : String,
    val title : String,
    val video : Boolean,
    val vote_average : Float,
    val vote_count : Int
)