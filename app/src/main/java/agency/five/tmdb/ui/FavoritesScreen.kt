package agency.five.tmdb.ui

import agency.five.tmdb.ui.Title
import agency.five.tmdb.ui.TypeList
import agency.five.tmdb.ui.MovieItemViewState
import agency.five.tmdb.ui.MovieCard
import agency.five.tmdb.ui.MoviesList
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*

@ExperimentalMaterialApi
@Composable
fun FavoritesScreen() {

    var movieItems by remember {
        mutableStateOf(
            listOf(
                MovieItemViewState(
                    id = 1,
                    title = "The Shawshank Redemption",
                    overview = "Overview",
                    imageUrl = "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX128_CR0,3,128,176_AL_.jpg"
                ),
                MovieItemViewState(
                    id = 2,
                    title = "The Godfather",
                    overview = "Overview",
                    imageUrl = "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_UX128_CR0,1,128,176_AL_.jpg"
                ),
                MovieItemViewState(
                    id = 3,
                    title = "The Dark Knight",
                    overview = "Overview",
                    imageUrl = "https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_UX128_CR0,3,128,176_AL_.jpg"
                )
            )
        )
    }

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "Android Vjestina", color = MaterialTheme.colors.onPrimary) }
            )
        }
    ) {
        LazyColumn() {
            item { Title("Favorites") }
            item { MoviesList(movieItems = movieItems) }
        }
    }

}