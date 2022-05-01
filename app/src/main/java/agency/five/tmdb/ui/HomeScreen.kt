package agency.five.tmdb.ui

import agency.five.tmdb.R
import agency.five.tmdb.ui.theme.BlueTitle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
fun HomeScreen(viewModel: HomeViewModel) {

    val moviesPopularState : State<List<MovieItem>> = viewModel.moviesPopular.collectAsState(initial = emptyList())
    val moviesStreamingState : State<List<MovieItem>> = viewModel.moviesStreaming.collectAsState(initial = emptyList())
    val moviesTVState : State<List<MovieItem>> = viewModel.moviesTV.collectAsState(initial = emptyList())
    val moviesOnRentState : State<List<MovieItem>> = viewModel.moviesOnRent.collectAsState(initial = emptyList())

    val moviesLists : List<List<MovieItem>> = listOf(moviesStreamingState.value, moviesTVState.value, moviesOnRentState.value, moviesPopularState.value)

    val typeList1 by remember {
        mutableStateOf(
            listOf(
                MovieGroup(
                    id = 1,
                    title = "Streaming",
                    marked = true
                ),
                MovieGroup(
                    id = 2,
                    title = "On TV",
                    marked = false
                ),
                MovieGroup(
                    id = 3,
                    title = "For Rent",
                    marked = false
                ),
                MovieGroup(
                    id = 4,
                    title = "In theaters",
                    marked = false
                )
            )
        )
    }

    val typeList2 by remember {
        mutableStateOf(
            listOf(
                MovieGroup(
                    id = 1,
                    title = "Movies",
                    marked = true
                ),
                MovieGroup(
                    id = 2,
                    title = "TV",
                    marked = false
                )
            )
        )
    }

    val typeList3 by remember {
        mutableStateOf(
            listOf(
                MovieGroup(
                    id = 1,
                    title = "Today",
                    marked = true
                ),
                MovieGroup(
                    id = 2,
                    title = "This week",
                    marked = false
                )
            )
        )
    }

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { BottomBar() }
    ) {
        LazyColumn() {
            item { ImageHeader() }
            item { SearchField() }
            item { Title("What's popular") }
            item { MoviesList(viewModel = viewModel, typeList = typeList1, moviesLists) }
            item { Title(title = "Free to watch") }
            item { MoviesList(viewModel = viewModel, typeList = typeList2, moviesLists)  }
            item { Title(title = "Trending") }
            item { MoviesList(viewModel = viewModel, typeList = typeList3, moviesLists) }
            item { BottomBarSpacer() }
        }
    }
}

@Composable
fun ImageHeader() {
    Column(
        modifier = Modifier
            .background(BlueTitle)
            .padding(dimensionResource(id = R.dimen.logo_padding))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Image(painter = painterResource(id = R.drawable.tmdblogo),
            contentDescription = "",
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.logo_height))
                .width(dimensionResource(id = R.dimen.logo_width))
        )
    }
}

@Composable
fun SearchField() {
    var text : String by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .padding(
                horizontal = dimensionResource(id = R.dimen.search_spacing),
                vertical = dimensionResource(id = R.dimen.search_spacing)
            )
            .fillMaxWidth(),
        value = text,
        placeholder = { Text("Search", style = MaterialTheme.typography.body1) },
        onValueChange = { text = it },
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        leadingIcon = {
            IconButton(
                onClick = {}
            ) {
                Image(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "",
                    modifier = Modifier
                        .width(dimensionResource(id = R.dimen.search_size))
                        .height(dimensionResource(id = R.dimen.search_size))
                )
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun Title(title : String) {
    Text(text = title,
        color = BlueTitle,
        modifier = Modifier.padding(
            horizontal = dimensionResource(id = R.dimen.horizontal_spacing),
            vertical = dimensionResource(id = R.dimen.vertical_spacing)
        ),
        style = MaterialTheme.typography.h1
    )
}

data class MovieGroup(
    val id: Int,
    val title: String,
    val marked : Boolean,
)

data class MovieItem(
    var id: Int,
    val title: String,
    val overview: String,
    val imageUrl: String,
    var liked: Boolean = false,
)

@Composable
fun MovieCard(
    viewModel : HomeViewModel,
    modifier: Modifier = Modifier,
    onMovieItemClick: () -> Unit = {},
    item: MovieItem,
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
        FavoriteButton(viewModel, item)
    }
}

@Composable
fun FavoriteButton(viewModel : HomeViewModel, movie : MovieItem) {
    var like : Boolean by remember { mutableStateOf(false) }
    like = movie.liked

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
                            movie.liked = true
                            runBlocking<Unit> {
                                viewModel.addFavoriteMovie(movie)
                            }
                        } else {
                            movie.liked = false
                            runBlocking<Unit> {
                                viewModel.removeFromFavorites(movie)
                            }
                        }
                    }
                )
        )
    }
}

@Composable
fun MoviesList(viewModel : HomeViewModel, typeList : List<MovieGroup>, items : List<List<MovieItem>>) {
    var selectedIndex : Int by remember { mutableStateOf(1) }
    LazyRow() {
        items(typeList) {
            val id = it.id
            Text(text = it.title,
                color = if (selectedIndex == id) BlueTitle else Color.Black,
                modifier = Modifier
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.horizontal_spacing),
                        vertical = dimensionResource(id = R.dimen.vertical_spacing)
                    )
                    .clickable { selectedIndex = id },
                style = if (selectedIndex == id) MaterialTheme.typography.h2 else MaterialTheme.typography.body1
            )
        }
    }
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(
            vertical = dimensionResource(id = R.dimen.home_movies_list_content_padding_vertical)
        )
    ) {
        items(items[selectedIndex - 1]) {
            MovieCard(
                viewModel = viewModel,
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.start_spacing),
                    top = dimensionResource(id = R.dimen.top_spacing),
                    end = dimensionResource(id = R.dimen.end_spacing),

                ),
                item = it,
                onMovieItemClick = {}
            )
        }
    }
}

@Composable
fun BottomBar() {
    BottomNavigation(
        elevation = 12.dp,
        backgroundColor = Color.White
    ) {
        var home : Boolean by remember { mutableStateOf(true) }
        BottomNavigationItem(icon = {
            Image(painter = if (home) painterResource(id = R.drawable.marked_home) else painterResource(id = R.drawable.home),
                "")
        },
            label = { Text(text = "Home") },
            selected = true,
            onClick = {
                home = true
                Router.navigateTo(Screen.HomeScreen)
            }
        )
        BottomNavigationItem(icon = {
            Image(painter = if (home) painterResource(id = R.drawable.favorites) else painterResource(id = R.drawable.marked_favorites),
                "")
        },
            label = { Text(text = "Favorites") },
            selected = false,
            onClick = {
                home = false
                Router.navigateTo(Screen.FavoritesScreen)
            }
        )
    }
}

@Composable
fun BottomBarSpacer() {
    Spacer(modifier = Modifier
        .padding(20.dp)
        .height(10.dp)
        .fillMaxWidth()
        .background(Color.White)
    )
}

/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TmdbTheme {
        FavoriteButton()
    }
}*/