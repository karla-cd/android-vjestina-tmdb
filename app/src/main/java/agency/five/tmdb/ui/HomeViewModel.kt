package agency.five.tmdb.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

var movieItems = listOf(
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

class HomeViewModel() : ViewModel() {
    private val _movies = MutableStateFlow(movieItems)
    private val movies = _movies.asStateFlow()

    var items: List<MovieItem> = emptyList()

    init {
        viewModelScope.launch {
            movies.collect { it
                items = it
            }
        }
    }
}