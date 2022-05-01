package agency.five.tmdb.ui

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface MovieRepository {
    suspend fun getPopularMovies() : Flow<List<MovieItem>>
    suspend fun getStreamingMovies() : Flow<List<MovieItem>>
    suspend fun getTVMovies() : Flow<List<MovieItem>>
    suspend fun getOnRentMovies() : Flow<List<MovieItem>>
    suspend fun getFavoriteMovies() : Flow<MutableList<MovieItem>>
    suspend fun addFavoriteMovie(movie : MovieItem) : List<MovieItem>
    suspend fun removeFromFavorites(movie : MovieItem) : List<MovieItem>
}

internal class MovieRepositoryImpl(private val movieApi : MovieApi) : MovieRepository {

    private val database : Database = Database()

    override suspend fun getPopularMovies() : Flow<List<MovieItem>> = flow {
        emit(movieApi.getPopularMovies())
    }

    override suspend fun getStreamingMovies() : Flow<List<MovieItem>> = flow {
        emit(movieApi.getStreamingMovies())
    }
    override suspend fun getTVMovies() : Flow<List<MovieItem>> = flow {
        emit(movieApi.getTVMovies())
    }
    override suspend fun getOnRentMovies() : Flow<List<MovieItem>> = flow {
        emit(movieApi.getOnRentMovies())
    }

    override suspend fun getFavoriteMovies(): Flow<MutableList<MovieItem>> = flow {
        emit(database.favoriteMovies)
    }

    override suspend fun addFavoriteMovie(movie: MovieItem): List<MovieItem> = database.addFavoriteMovie(movie)

    override suspend fun removeFromFavorites(movie: MovieItem): List<MovieItem> = database.removeFromFavorites(movie)
}