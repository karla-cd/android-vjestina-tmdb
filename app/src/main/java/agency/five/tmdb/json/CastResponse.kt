package agency.five.tmdb.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CastResponse(
    @SerialName("cast")
    val roles: List<Role>,
    @SerialName("crew")
    val crew: List<CrewMember>
)