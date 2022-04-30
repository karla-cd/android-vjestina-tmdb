package agency.five.tmdb.ui

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow

interface MovieRepository {
    suspend fun getPopularMovies() : List<MovieItem>
    suspend fun getStreamingMovies() : List<MovieItem>
    suspend fun getTVMovies() : List<MovieItem>
    suspend fun getOnRentMovies() : List<MovieItem>
}

internal class MovieRepositoryImpl(
    private val movieApi : MovieApi
) : MovieRepository {
    override suspend fun getPopularMovies(): List<MovieItem> = movieApi.getPopularMovies()
    override suspend fun getStreamingMovies(): List<MovieItem> = movieApi.getStreamingMovies()
    override suspend fun getTVMovies(): List<MovieItem> = movieApi.getTVMovies()
    override suspend fun getOnRentMovies(): List<MovieItem> = movieApi.getOnRentMovies()
}