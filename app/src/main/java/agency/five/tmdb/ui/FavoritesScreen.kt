package agency.five.tmdb.ui

import agency.five.tmdb.ui.Title
import agency.five.tmdb.ui.TypeList
import agency.five.tmdb.ui.MovieItem
import agency.five.tmdb.ui.MovieCard
import agency.five.tmdb.ui.MoviesList
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalMaterialApi
@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel) {

   val moviesFavoriteState : State<List<MovieItem>> = viewModel.favoriteMovies.collectAsState(initial = emptyList())

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { BottomBar() }
    ) {
        LazyColumn() {
            item { ImageHeader() }
            item {
                Spacer(modifier = Modifier
                    .height(20.dp)
                    .fillMaxWidth()
                    .background(Color.White)
                ) }
            item { Title("Favorites") }
            item { MoviesList(movieItems = moviesFavoriteState.value) }
        }
    }

}