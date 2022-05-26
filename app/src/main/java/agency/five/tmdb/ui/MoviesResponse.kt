package agency.five.tmdb.ui

import kotlinx.serialization.SerialName
import kotlinx.serialization.*

@Serializable
data class MoviesResponse(
    @SerialName("results")
    val movies: List<Movie>
)