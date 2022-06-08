package agency.five.tmdb.ui

import agency.five.tmdb.R
import agency.five.tmdb.json.CrewMember
import agency.five.tmdb.json.MovieDetailsResponse
import agency.five.tmdb.json.Role
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter

@Composable
fun DetailsScreen(navController : NavController, viewModel : HomeViewModel, movieId : Int) {

    val movieDetails = viewModel.getMovieDetails(movieId).collectAsState(initial = null).value

    val movie = MovieDetailsResponse(
        adult = movieDetails?.adult ?: false,
        backdrop_path = movieDetails?.backdrop_path ?: "",
        belongs_to_collection = movieDetails?.belongs_to_collection,
        budget = movieDetails?.budget ?: 0,
        genres = movieDetails?.genres ?: emptyList(),
        homepage = movieDetails?.homepage ?: "",
        id = movieDetails?.id ?: 0,
        imdb_id = movieDetails?.imdb_id ?: "",
        original_language = movieDetails?.original_language ?: "",
        original_title = movieDetails?.original_title ?: "",
        overview = movieDetails?.overview ?: "",
        popularity = movieDetails?.popularity ?: 0.0f,
        poster_path = movieDetails?.poster_path ?: "",
        production_companies = movieDetails?.production_companies ?: emptyList(),
        production_countries = movieDetails?.production_countries ?: emptyList(),
        release_date = movieDetails?.release_date ?: "",
        revenue = movieDetails?.revenue ?: 0,
        runtime = movieDetails?.runtime ?: 0,
        spoken_languages = movieDetails?.spoken_languages ?: emptyList(),
        status = movieDetails?.status ?: "",
        tagline = movieDetails?.tagline ?: "",
        title = movieDetails?.title ?: "",
        video = movieDetails?.video ?: false,
        vote_average = movieDetails?.vote_average ?: 0.0f,
        vote_count = movieDetails?.vote_count ?: 0
    )

    val roles = viewModel.getMovieCast(movieId).collectAsState(initial = null).value ?: emptyList()

    val crew = viewModel.getMovieCrew(movieId).collectAsState(initial = null).value ?: emptyList()

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { ImageHeaderWithBackArrow(navController) },
    ) {
        LazyColumn() {
            item {
                MovieHeader(
                    movie.title,
                    movie.release_date,
                    "Action, Science Fiction, Adventure  2h 6m",
                    movie.backdrop_path
                )
            }
            item { Title("Overview") }
            item { Overview(overview = movie.overview) }
            item { ProductionMembers(productionMembers = crew) }
            item { Title("Top Billed Cast") }
            item { Actors(actors = roles) }

        }
    }
    BackPressHandler(onBackPressed = { Router.navigateTo(Screen.HomeScreen) })
}

@Composable
fun ImageHeaderWithBackArrow(navController : NavController) {
    Row(
        modifier = Modifier
            .background(BlueTitle)
            .padding(dimensionResource(id = R.dimen.logo_padding))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(painter = painterResource(id = R.drawable.back_arrow),
            contentDescription = "",
            modifier = Modifier.clickable(
                onClick = {
                    navController.navigate("homeScreen")
                }
            )
        )
        Image(painter = painterResource(id = R.drawable.tmdblogo),
            contentDescription = "",
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.logo_height))
                .width(dimensionResource(id = R.dimen.logo_width)))
    }
}

@Composable
fun MovieHeader(title : String, date : String, genre : String, backdrop_path : String) {
    Box() {
        Image(painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500$backdrop_path"),
            contentDescription = "",
            modifier = Modifier
                .height(230.dp)
                .width(600.dp),
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.title_left_padding),
                top = dimensionResource(id = R.dimen.title_top_padding)
            )
        ) {
            Text(text = title,
                color = Color.White,
                fontSize = 30.sp
            )
            Text(text = date,
                color = Color.White
            )
            Text(text = genre,
                color = Color.White
            )
            StarButton()
        }
    }
}

@Composable
fun StarButton() {
    Surface(modifier = Modifier
        .padding(
            start = dimensionResource(id = R.dimen.small_spacing),
            top = dimensionResource(id = R.dimen.small_spacing)
        ), color = Color.Transparent) {
        Image(
            painter = painterResource(id = R.drawable.star),
            contentDescription = "",
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.heart_size))
                .background(BlueTitle, CircleShape)
                .padding(dimensionResource(id = R.dimen.heart_circle))
        )
    }
}

@Composable
fun Overview(overview : String) {
    Text(text = overview,
        modifier = Modifier.padding(
            horizontal = dimensionResource(id = R.dimen.horizontal_spacing),
            vertical = dimensionResource(id = R.dimen.vertical_spacing)
        ),
        style = MaterialTheme.typography.body1
    )
}

data class ProductionMember(
    val id : Int, 
    val name : String, 
    val role : String
)

@Composable
fun ProductionMembers(productionMembers : List<CrewMember>) {
    LazyRow() {
        items(productionMembers) {
            val id = it.id
            Column() {
                Text(text = it.name,
                    modifier = Modifier
                        .padding(
                            horizontal = dimensionResource(id = R.dimen.horizontal_spacing),
                            vertical = dimensionResource(id = R.dimen.vertical_spacing)
                        ),
                    style = MaterialTheme.typography.h1
                )
                Text(
                    text = it.known_for_department,
                    modifier = Modifier
                        .padding(
                            horizontal = dimensionResource(id = R.dimen.horizontal_spacing),
                            vertical = 0.dp
                        ),
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}

data class Actor(
    val id : Int,
    val name : String,
    val role : String,
    val imageUrl : String,
)

@Composable
fun ActorCard(modifier : Modifier, actor : Role) {
    Box(
        modifier = modifier.shadow(2.dp),
    ) {
        Image(
            painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${actor.profile_path}"),
            contentDescription = null,
            modifier = Modifier
                .size(
                    width = dimensionResource(id = R.dimen.movie_card_width),
                    height = dimensionResource(id = R.dimen.movie_card_height)
                )
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .padding(top = 160.dp)
                .background(Color.White)
            ,
        ) {
            Column() {
                Text(
                    text = actor.name,
                    modifier = Modifier.width(width = dimensionResource(id = R.dimen.movie_card_width)),
                    style = MaterialTheme.typography.h2
                )
                Text(
                    text = actor.character,
                    modifier = Modifier.width(width = dimensionResource(id = R.dimen.movie_card_width)),
                    style = MaterialTheme.typography.body2,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun Actors(actors : List<Role>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(
            vertical = dimensionResource(id = R.dimen.home_movies_list_content_padding_vertical)
        )
    ) {
        items(actors) {
            val id = it.id
            ActorCard(
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.start_spacing),
                    top = dimensionResource(id = R.dimen.top_spacing),
                    end = dimensionResource(id = R.dimen.end_spacing),
                ),
                actor = it,
            )
        }
    }
}
