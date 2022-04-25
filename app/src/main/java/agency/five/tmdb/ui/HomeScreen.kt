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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moviesViewModelModule = module {
    viewModel {
        HomeViewModel()
    }
}

@ExperimentalMaterialApi
@Composable
fun HomeScreen(viewModel: HomeViewModel) {

    var typeList1 by remember {
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

    var typeList2 by remember {
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

    var typeList3 by remember {
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
            item { TypeList(typeList = typeList1) }
            item { MoviesList(movieItems = viewModel.items) }
            item { Title(title = "Free to watch") }
            item { TypeList(typeList = typeList2) }
            item { MoviesList(movieItems = viewModel.items) }
            item { Title(title = "Trending") }
            item { TypeList(typeList = typeList3) }
            item { MoviesList(movieItems = viewModel.items) }
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
                .width(dimensionResource(id = R.dimen.logo_width)))
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
    val marked : Boolean
)

fun markAnotherItem(typeList : List<MovieGroup>, id : Int) {}

@Composable
fun TypeList(typeList : List<MovieGroup>) {
    var selectedIndex : Int by remember { mutableStateOf(1) }
    LazyRow() {
        items(typeList) {
            val id = it.id
            Text(text = it.title,
                color = if (selectedIndex == id) BlueTitle else Color.Black,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(id = R.dimen.horizontal_spacing),
                    vertical = dimensionResource(id = R.dimen.vertical_spacing)
                ).clickable{ selectedIndex = id },
                style = if (selectedIndex == id) MaterialTheme.typography.h2 else MaterialTheme.typography.body1
            )
        }
    }
}

data class MovieItem(
    val id: Int,
    val title: String,
    val overview: String,
    val imageUrl: String
)

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
        FavoriteButton(item)
    }
}

// val favoritesList : MutableList<MovieItemViewState> by remember { mutableListOf<MovieItemViewState>() }

@Composable
fun FavoriteButton(movie : MovieItem) {
    var hello : Boolean by remember { mutableStateOf(false) }

    Surface(modifier = Modifier
        .padding(
            start = dimensionResource(id = R.dimen.small_spacing),
            top = dimensionResource(id = R.dimen.small_spacing)
        ),
        color = Color.Transparent
    ) {
        Image(
            painter = if(hello) {
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
                        hello = !hello
                        // favoritesList.add(movie)
                    }
                )
        )
    }
}

@Composable
fun MoviesList(
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
fun BottomBar() {
    var home : Boolean by remember { mutableStateOf(true) }
    BottomNavigation(
        elevation = 12.dp,
        backgroundColor = Color.White
    ) {
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