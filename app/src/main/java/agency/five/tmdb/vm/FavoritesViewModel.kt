package agency.five.tmdb.vm

import agency.five.tmdb.json.Movie
import agency.five.tmdb.di.repo.MovieRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(private val movieRepository : MovieRepository) : ViewModel() {

    public var moviesFavorite : Flow<List<Movie>> = MutableStateFlow(emptyList())

    public suspend fun removeFromFavorites(movie : Movie) = movieRepository.removeFromFavorites(movie)

    init {
        viewModelScope.launch {
            moviesFavorite = movieRepository.getFavoriteMovies()
        }
    }
}