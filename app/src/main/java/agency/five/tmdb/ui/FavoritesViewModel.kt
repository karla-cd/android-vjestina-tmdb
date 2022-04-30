package agency.five.tmdb.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(val movieRepository : MovieRepository) : ViewModel() {
    private val _favoriteMovies : MutableStateFlow<List<MovieItem>> = MutableStateFlow(emptyList())
    public val favoriteMovies : Flow<List<MovieItem>> = _favoriteMovies

    init {
        viewModelScope.launch {
            favoriteMovies.collect { it
                val favoriteMoviesList = movieRepository.getPopularMovies()
                _favoriteMovies.emit(favoriteMoviesList)
            }
        }
    }
}