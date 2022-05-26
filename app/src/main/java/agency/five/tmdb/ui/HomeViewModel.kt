package agency.five.tmdb.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val movieRepository : MovieRepository, val movieApi : MovieApi) : ViewModel() {

    public var moviesPopular : Flow<List<Movie>> = MutableStateFlow(emptyList())
    public var moviesStreaming : Flow<List<Movie>> = MutableStateFlow(emptyList())
    public var moviesTV : Flow<List<Movie>> = MutableStateFlow(emptyList())
    public var moviesOnRent : Flow<List<Movie>> = MutableStateFlow(emptyList())

    public suspend fun addFavoriteMovie(movie : Movie) = movieRepository.addFavoriteMovie(movie)

    public suspend fun removeFromFavorites(movie : Movie) = movieRepository.removeFromFavorites(movie)

    init {
        viewModelScope.launch {
            moviesPopular = movieRepository.getPopularMovies()
            moviesStreaming = movieRepository.getStreamingMovies()
            moviesTV = movieRepository.getTVMovies()
            moviesOnRent = movieRepository.getOnRentMovies()
        }
    }
}