package agency.five.tmdb.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

var favoriteMovieItems = listOf(
    MovieItem(
        id = 1,
        title = "The Shawshank Redemption",
        overview = "Overview",
        imageUrl = "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX128_CR0,3,128,176_AL_.jpg"
    ),
    MovieItem(
        id = 2,
        title = "The Godfather",
        overview = "Overview",
        imageUrl = "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_UX128_CR0,1,128,176_AL_.jpg"
    ),
    MovieItem(
        id = 3,
        title = "The Dark Knight",
        overview = "Overview",
        imageUrl = "https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_UX128_CR0,3,128,176_AL_.jpg"
    )
)

class FavoritesViewModel() : ViewModel() {
    private val _favoriteMovies = MutableStateFlow(favoriteMovieItems)
    private val favoriteMovies = _favoriteMovies.asStateFlow()

    private var items: List<MovieItem> = emptyList()

    init {
        viewModelScope.launch {
            favoriteMovies.collect { it
                items = it
            }
        }
    }
}