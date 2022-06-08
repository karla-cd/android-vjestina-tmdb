package agency.five.tmdb.ui

import agency.five.tmdb.R
import agency.five.tmdb.json.Movie
import agency.five.tmdb.ui.theme.BlueTitle
import agency.five.tmdb.vm.HomeViewModel
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
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.runBlocking

@ExperimentalMaterialApi
@Composable
fun HomeScreen(navController : NavController, viewModel: HomeViewModel) {

    val moviesPopularState : State<List<Movie>> = viewModel.moviesPopular.collectAsState(initial = emptyList())
    val moviesNowPlayingState : State<List<Movie>> = viewModel.moviesNowPlaying.collectAsState(initial = emptyList())
    val moviesUpcomingState : State<List<Movie>> = viewModel.moviesUpcoming.collectAsState(initial = emptyList())
    val moviesTopRatedState : State<List<Movie>> = viewModel.moviesTopRated.collectAsState(initial = emptyList())

    val moviesLists1 : List<List<Movie>> = listOf(moviesPopularState.value)
    val moviesLists2 : List<List<Movie>> = listOf(moviesTopRatedState.value)
    val moviesLists3 : List<List<Movie>> = listOf(moviesNowPlayingState.value, moviesUpcomingState.value)

    val typeList1 by remember {
        mutableStateOf(
            listOf(
                MovieGroup(
                    id = 1,
                    title = "Popular",
                    marked = true
                ),
            )
        )
    }

    val typeList2 by remember {
        mutableStateOf(
            listOf(
                MovieGroup(
                    id = 1,
                    title = "Top rated",
                    marked = true
                ),
            )
        )
    }

    val typeList3 by remember {
        mutableStateOf(
            listOf(
                MovieGroup(
                    id = 1,
                    title = "Now playing",
                    marked = true
                ),
                MovieGroup(
                    id = 2,
                    title = "Upcoming",
                    marked = false
                )
            )
        )
    }

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { ImageHeader() },
        bottomBar = { BottomBar() }
    ) {
        LazyColumn() {
            item { SearchField() }
            item { Title("What's popular") }
            item { MoviesList(navController = navController, viewModel = viewModel, typeList = typeList1, moviesLists1) }
            item { Title(title = "Free to watch") }
            item { MoviesList(navController = navController, viewModel = viewModel, typeList = typeList2, moviesLists2)  }
            item { Title(title = "Trending") }
            item { MoviesList(navController = navController, viewModel = viewModel, typeList = typeList3, moviesLists3) }
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
            .fillMaxWidth(),
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

@Composable
fun MovieCard(
    navController: NavController,
    viewModel : HomeViewModel,
    modifier: Modifier = Modifier,
    onMovieItemClick: () -> Unit = {},
    item: Movie,
) {
    Box(
        modifier = modifier.clickable { onMovieItemClick() }
    ) {
        Image(
            painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500" + item.poster_path),
            contentDescription = null,
            modifier = Modifier
                .size(
                    width = dimensionResource(id = R.dimen.movie_card_width),
                    height = dimensionResource(id = R.dimen.movie_card_height)
                )
                .clip(RoundedCornerShape(12.dp))
                .clickable(
                    onClick = {
                        //Router.navigateTo(Screen.DetailsScreen)
                        navController.navigate("homeScreen/" + item.id)
                    }
                ),
            contentScale = ContentScale.Crop
        )
        FavoriteButton(viewModel, item)
    }
}

@Composable
fun FavoriteButton(viewModel : HomeViewModel, movie : Movie) {
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
                                viewModel.addFavoriteMovie(movie)
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

@Composable
fun MoviesList(navController: NavController, viewModel : HomeViewModel, typeList : List<MovieGroup>, items : List<List<Movie>>) {
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
                navController = navController,
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