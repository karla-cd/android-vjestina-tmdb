package agency.five.tmdb.ui

interface MovieApi {
    suspend fun getPopularMovies() : List<MovieItem>
    suspend fun getStreamingMovies() : List<MovieItem>
    suspend fun getTVMovies() : List<MovieItem>
    suspend fun getOnRentMovies() : List<MovieItem>
}

internal class MovieApiImpl : MovieApi {

    private val database : Database = Database()

    override suspend fun getPopularMovies(): List<MovieItem> = database.popularMovies

    override suspend fun getStreamingMovies(): List<MovieItem> = database.streamingMovies

    override suspend fun getTVMovies(): List<MovieItem> = database.tVMovies

    override suspend fun getOnRentMovies(): List<MovieItem> = database.onRentMovies

}