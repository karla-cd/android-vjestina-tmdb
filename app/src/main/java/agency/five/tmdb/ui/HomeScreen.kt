package agency.five.tmdb.ui

import agency.five.tmdb.ui.theme.TmdbTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import agency.five.tmdb.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import coil.compose.rememberImagePainter


@ExperimentalMaterialApi
@Composable
fun HomeScreen() {

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

    var typeList1 by remember {
        mutableStateOf(
            listOf("Streaming", "On TV", "For Rent", "In theaters")
        )
    }

    var typeList2 by remember {
        mutableStateOf(
            listOf("Movies", "TV")
        )
    }

    var typeList3 by remember {
        mutableStateOf(
            listOf("Today", "This week")
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
            item { SearchField() }
            item { Title("What's popular") }
            item { TypeList(typeList = typeList1) }
            item { MoviesList(movieItems = movieItems) }
            item { Title(title = "Free to watch") }
            item { TypeList(typeList = typeList2) }
            item { MoviesList(movieItems = movieItems) }
            item { Title(title = "Trending") }
            item { TypeList(typeList = typeList3) }
            item { MoviesList(movieItems = movieItems) }
        }
    }
}



@Composable
fun SearchField() {
    val text = "Search"
    TextField(
        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.search_spacing), vertical = dimensionResource(id = R.dimen.search_spacing)),
        value = text,
        onValueChange = {},
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        leadingIcon = {
            IconButton(
                modifier = Modifier.alpha(ContentAlpha.medium),
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search icon",
                    tint = Color.Blue
                )
            }
        }
    )
}

data class MovieItemViewState(
    val id: Int,
    val title: String,
    val overview: String,
    val imageUrl: String
)

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    onMovieItemClick: () -> Unit = {},
    item: MovieItemViewState
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
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.small_spacing))),
            contentScale = ContentScale.Crop
        )
        /*
        FavoriteButton(
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.small_spacing),
                top = dimensionResource(id = R.dimen.small_spacing)
            )
        )*/

    }
}

@Composable
private fun MoviesList(
    modifier: Modifier = Modifier,
    onMovieItemClick: (MovieItemViewState) -> Unit = {},
    movieItems: List<MovieItemViewState>
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.home_movies_list_content_padding))
    ) {
        items(movieItems) {
            MovieCard(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.micro_spacing), vertical = dimensionResource(id = R.dimen.macro_spacing)),
                item = it,
                onMovieItemClick = {}
            )
        }
    }
}

@Composable
fun Title(title : String) {
    Text(text = title, modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.micro_spacing), vertical = dimensionResource(id = R.dimen.macro_spacing)))
}

@Composable
fun TypeList(typeList : List<String>) {
    LazyRow() {
        items(typeList) {
            Text(text = it, modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.micro_spacing), vertical = dimensionResource(id = R.dimen.macro_spacing)))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TmdbTheme {
        SearchField()
    }
}