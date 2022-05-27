package agency.five.tmdb.ui

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface MovieRepository {
    suspend fun getPopularMovies() : Flow<List<Movie>>
    suspend fun getNowPlayingMovies() : Flow<List<Movie>>
    suspend fun getUpcomingMovies() : Flow<List<Movie>>
    suspend fun getTopRatedMovies() : Flow<List<Movie>>
    suspend fun getFavoriteMovies() : Flow<MutableList<Movie>>
    suspend fun addFavoriteMovie(movie : Movie) : List<Movie>
    suspend fun removeFromFavorites(movie : Movie) : List<Movie>
    suspend fun getMovieDetails(movieId : Int) : MovieDetailsResponse
}

internal class MovieRepositoryImpl(private val movieApi : MovieApi, private val database : Database) : MovieRepository {

    override suspend fun getPopularMovies() : Flow<List<Movie>> = flow {
        emit(movieApi.getPopularMovies().movies)
    }

    override suspend fun getNowPlayingMovies() : Flow<List<Movie>> = flow {
        emit(movieApi.getNowPlayingMovies().movies)
    }
    override suspend fun getUpcomingMovies() : Flow<List<Movie>> = flow {
        emit(movieApi.getUpcomingMovies().movies)
    }
    override suspend fun getTopRatedMovies() : Flow<List<Movie>> = flow {
        emit(movieApi.getTopRatedMovies().movies)
    }

    override suspend fun getFavoriteMovies(): Flow<MutableList<Movie>> = flow {
        emit(database.favoriteMovies)
    }

    override suspend fun addFavoriteMovie(movie: Movie): List<Movie> = database.addFavoriteMovie(movie)

    override suspend fun removeFromFavorites(movie: Movie): List<Movie> = database.removeFromFavorites(movie)

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsResponse = movieApi.getMovieDetails(movieId)
}