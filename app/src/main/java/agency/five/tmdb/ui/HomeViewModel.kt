package agency.five.tmdb.ui

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

    suspend fun getMovieDetails(movieId : Int) : MovieDetailsResponse = movieRepository.getMovieDetails(movieId)

    init {
        viewModelScope.launch {
            moviesPopular = movieRepository.getPopularMovies()
            moviesNowPlaying = movieRepository.getNowPlayingMovies()
            moviesUpcoming = movieRepository.getUpcomingMovies()
            moviesTopRated = movieRepository.getTopRatedMovies()
        }
    }
}