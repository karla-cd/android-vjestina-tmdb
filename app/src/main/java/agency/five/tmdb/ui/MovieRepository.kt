package agency.five.tmdb.ui

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface MovieRepository {
    suspend fun getPopularMovies() : Flow<List<Movie>>
    suspend fun getStreamingMovies() : Flow<List<Movie>>
    suspend fun getTVMovies() : Flow<List<Movie>>
    suspend fun getOnRentMovies() : Flow<List<Movie>>
    suspend fun getFavoriteMovies() : Flow<MutableList<Movie>>
    suspend fun addFavoriteMovie(movie : Movie) : List<Movie>
    suspend fun removeFromFavorites(movie : Movie) : List<Movie>
}

internal class MovieRepositoryImpl(private val movieApi : MovieApi, private val database : Database) : MovieRepository {

    override suspend fun getPopularMovies() : Flow<List<Movie>> = flow {
        emit(movieApi.getPopularMovies().movies)
    }

    override suspend fun getStreamingMovies() : Flow<List<Movie>> = flow {
        emit(movieApi.getStreamingMovies().movies)
    }
    override suspend fun getTVMovies() : Flow<List<Movie>> = flow {
        emit(movieApi.getTVMovies().movies)
    }
    override suspend fun getOnRentMovies() : Flow<List<Movie>> = flow {
        emit(movieApi.getOnRentMovies().movies)
    }

    override suspend fun getFavoriteMovies(): Flow<MutableList<Movie>> = flow {
        emit(database.favoriteMovies)
    }

    override suspend fun addFavoriteMovie(movie: Movie): List<Movie> = database.addFavoriteMovie(movie)

    override suspend fun removeFromFavorites(movie: Movie): List<Movie> = database.removeFromFavorites(movie)
}