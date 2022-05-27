package agency.five.tmdb.ui

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val id : Int,
    val name : String
)