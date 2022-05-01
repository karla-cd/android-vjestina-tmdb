package agency.five.tmdb.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(val movieRepository : MovieRepository) : ViewModel() {

    public var moviesPopular : Flow<List<MovieItem>> = MutableStateFlow(emptyList())
    public var moviesStreaming : Flow<List<MovieItem>> = MutableStateFlow(emptyList())
    public var moviesTV : Flow<List<MovieItem>> = MutableStateFlow(emptyList())
    public var moviesOnRent : Flow<List<MovieItem>> = MutableStateFlow(emptyList())

    public suspend fun addFavoriteMovie(movie : MovieItem) = movieRepository.addFavoriteMovie(movie)

    public suspend fun removeFromFavorites(movie : MovieItem) = movieRepository.removeFromFavorites(movie)

    init {
        viewModelScope.launch {
            moviesPopular = movieRepository.getPopularMovies()
            moviesStreaming = movieRepository.getStreamingMovies()
            moviesTV = movieRepository.getTVMovies()
            moviesOnRent = movieRepository.getOnRentMovies()
        }
    }
}