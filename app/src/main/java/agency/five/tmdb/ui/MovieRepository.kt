package agency.five.tmdb.ui

import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies() : Flow<List<MovieItem>>
}

internal class MovieRepositoryImpl(
    private val movieApi : MovieApi
) : MovieRepository {
    override fun getPopularMovies(): Flow<List<MovieItem>> {
        TODO("Not yet implemented")
    }
}