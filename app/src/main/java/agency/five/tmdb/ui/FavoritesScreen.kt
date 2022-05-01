package agency.five.tmdb.ui

import agency.five.tmdb.R
import agency.five.tmdb.di.modules.viewModelModule
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@ExperimentalMaterialApi
@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel) {

    val moviesFavoriteState : State<List<MovieItem>> = viewModel.moviesFavorite.collectAsState(initial = emptyList())

    // LazyGrid ovde triba koristiti

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
            item { MoviesGrid(movieItems = moviesFavoriteState.value) }
        }
    }
}


@Composable
fun MoviesGrid(
    modifier: Modifier = Modifier.padding(
        horizontal = dimensionResource(id = R.dimen.home_movies_list_padding)
    ),
    onMovieItemClick: (MovieItem) -> Unit = {},
    movieItems: List<MovieItem>
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(
            vertical = dimensionResource(id = R.dimen.home_movies_list_content_padding_vertical)
        )
    ) {
        items(movieItems) {
            MovieCard(
                modifier = Modifier.padding(
                    horizontal = dimensionResource(id = R.dimen.horizontal_spacing),
                    vertical = dimensionResource(id = R.dimen.vertical_spacing)
                ),
                item = it,
                onMovieItemClick = {}
            )
        }
    }
}

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    onMovieItemClick: () -> Unit = {},
    item: MovieItem
) {
    Box(
        modifier = modifier.clickable { onMovieItemClick() }
    ) {
        Image(
            painter = rememberImagePainter(item.imageUrl),
            contentDescription = null,
            modifier = Modifier
                .size(
                    width = dimensionResource(id = R.dimen.movie_card_width),
                    height = dimensionResource(id = R.dimen.movie_card_height)
                )
                .clip(RoundedCornerShape(12.dp))
                .clickable(
                    onClick = {
                        Router.navigateTo(Screen.DetailsScreen)
                    }
                ),
            contentScale = ContentScale.Crop
        )
        //FavoriteButton(viewModel, item)
    }
}

