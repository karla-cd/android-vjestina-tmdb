package agency.five.tmdb.ui

import agency.five.tmdb.R
import agency.five.tmdb.ui.theme.BlueTitle
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.runBlocking

@ExperimentalMaterialApi
@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel) {

    val moviesFavoriteState : State<List<Movie>> = viewModel.moviesFavorite.collectAsState(initial = emptyList())

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
        }
        MoviesGrid(viewModel = viewModel, movieItems = moviesFavoriteState.value)
    }
}


@Composable
fun MovieCard(
    viewModel: FavoritesViewModel,
    modifier: Modifier = Modifier,
    onMovieItemClick: () -> Unit = {},
    item: Movie
) {
    Box(
        modifier = modifier.clickable { onMovieItemClick() }
    ) {
        Image(
            painter = rememberImagePainter("https://image.tmdb.org/t/p/w500" + item.poster_path),
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
        FavoriteButton(viewModel, item)
    }
}

@Composable
fun FavoriteButton(viewModel : FavoritesViewModel, movie : Movie) {
    var like : Boolean by remember { mutableStateOf(false) }
    like = movie.adult

    Surface(modifier = Modifier
        .padding(
            start = dimensionResource(id = R.dimen.small_spacing),
            top = dimensionResource(id = R.dimen.small_spacing)
        ),
        color = Color.Transparent
    ) {
        Image(
            painter = if(like) {
                painterResource(id = R.drawable.full_heart)
            } else {
                painterResource(id = R.drawable.heart)
            },
            contentDescription = "",
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.heart_size))
                .background(BlueTitle.copy(alpha = 0.7f), CircleShape)
                .padding(dimensionResource(id = R.dimen.heart_circle))
                .clickable(
                    onClick = {
                        like = !like
                        if (like) {
                            movie.adult = true
                            runBlocking<Unit> {
                                //viewModel.addFavoriteMovie(movie)
                            }
                        } else {
                            movie.adult = false
                            runBlocking<Unit> {
                                viewModel.removeFromFavorites(movie)
                            }
                        }
                    }
                )
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoviesGrid(
    viewModel: FavoritesViewModel,
    movieItems: List<Movie>
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            start = 12.dp,
            end = 12.dp
        ),
        content = {
            items(movieItems) {
                MovieCard(
                    viewModel = viewModel,
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(id = R.dimen.horizontal_spacing),
                        vertical = dimensionResource(id = R.dimen.vertical_spacing)
                    ),
                    item = it,
                    onMovieItemClick = {}
                )
            }
        },
        modifier = Modifier
            .padding(horizontal = 0.dp, vertical = 150.dp)
            .padding(PaddingValues(bottom = 20.dp)),
    )
}

