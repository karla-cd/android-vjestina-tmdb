package agency.five.tmdb.json

import kotlinx.serialization.Serializable

@Serializable
data class CrewMember(
    val adult : Boolean,
    val gender : Int,
    val id : Int,
    val known_for_department : String,
    val name : String,
    val original_name : String,
    val popularity : Float,
    val credit_id : String,
    val department : String,
    val job : String
)