package agency.five.tmdb.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(val movieRepository : MovieRepository) : ViewModel() {

    public var moviesFavorite : Flow<List<MovieItem>> = MutableStateFlow(emptyList())

    public suspend fun removeFromFavorites(movie : MovieItem) = movieRepository.removeFromFavorites(movie)

    init {
        viewModelScope.launch {
            moviesFavorite = movieRepository.getFavoriteMovies()
        }
    }
}