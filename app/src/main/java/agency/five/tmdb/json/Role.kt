package agency.five.tmdb.json

import kotlinx.serialization.Serializable

@Serializable
data class Role(
    val adult : Boolean,
    val gender : Int,
    val id : Int,
    val known_for_department : String,
    val name : String,
    val original_name : String,
    val popularity : Float,
    val profile_path : String?,
    val cast_id : Int,
    val character : String,
    val credit_id : String,
    val order : Int
)