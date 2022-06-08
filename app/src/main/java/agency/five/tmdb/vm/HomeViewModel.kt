package agency.five.tmdb.vm

import agency.five.tmdb.json.Movie
import agency.five.tmdb.json.MovieDetailsResponse
import agency.five.tmdb.di.api.MovieApi
import agency.five.tmdb.di.repo.MovieRepository
import agency.five.tmdb.json.CrewMember
import agency.five.tmdb.json.Role
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val movieRepository : MovieRepository, val movieApi : MovieApi) : ViewModel() {

    var moviesPopular : Flow<List<Movie>> = MutableStateFlow(emptyList())
    var moviesNowPlaying : Flow<List<Movie>> = MutableStateFlow(emptyList())
    var moviesUpcoming : Flow<List<Movie>> = MutableStateFlow(emptyList())
    var moviesTopRated : Flow<List<Movie>> = MutableStateFlow(emptyList())

    suspend fun addFavoriteMovie(movie : Movie) = movieRepository.addFavoriteMovie(movie)

    suspend fun removeFromFavorites(movie : Movie) = movieRepository.removeFromFavorites(movie)

    fun getMovieDetails(movieId : Int) : Flow<MovieDetailsResponse> = movieRepository.getMovieDetails(movieId)

    fun getMovieCast(movieId : Int) : Flow<List<Role>> = movieRepository.getMovieCast(movieId)

    fun getMovieCrew(movieId: Int) : Flow<List<CrewMember>> = movieRepository.getMovieCrew(movieId)

    init {
        viewModelScope.launch {
            moviesPopular = movieRepository.getPopularMovies()
            moviesNowPlaying = movieRepository.getNowPlayingMovies()
            moviesUpcoming = movieRepository.getUpcomingMovies()
            moviesTopRated = movieRepository.getTopRatedMovies()
        }
    }
}