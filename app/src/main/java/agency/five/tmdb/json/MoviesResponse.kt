package agency.five.tmdb.json

import agency.five.tmdb.json.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.*

@Serializable
data class MoviesResponse(
    @SerialName("results")
    val movies: List<Movie>
)