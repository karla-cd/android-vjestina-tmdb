package agency.five.tmdb.di.repo

import agency.five.tmdb.json.Movie
import agency.five.tmdb.json.MovieDetailsResponse
import agency.five.tmdb.di.db.Database
import agency.five.tmdb.di.api.MovieApi
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
    fun getMovieDetails(movieId : Int) : Flow<MovieDetailsResponse>
}

internal class MovieRepositoryImpl(private val movieApi : MovieApi, private val database : Database) :
    MovieRepository {

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

    override suspend fun getFavoriteMovies() : Flow<MutableList<Movie>> = flow {
        emit(database.favoriteMovies)
    }

    override suspend fun addFavoriteMovie(movie: Movie) : List<Movie> = database.addFavoriteMovie(movie)

    override suspend fun removeFromFavorites(movie: Movie) : List<Movie> = database.removeFromFavorites(movie)

    override fun getMovieDetails(movieId: Int): Flow<MovieDetailsResponse> = flow {
        emit(movieApi.getMovieDetails(movieId))
    }

}