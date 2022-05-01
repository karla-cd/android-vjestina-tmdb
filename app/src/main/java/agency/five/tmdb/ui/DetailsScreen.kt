package agency.five.tmdb.ui

import agency.five.tmdb.R
import agency.five.tmdb.ui.theme.BlueTitle
import agency.five.tmdb.ui.theme.TmdbTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter

@Composable
fun DetailsScreen() {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        LazyColumn() {
            item { ImageHeaderWithBackArrow() }
            item { MovieHeader("Iron man (2008)", "05/02/2008 (US)", "Action, Science Fiction, Adventure  2h 6m") }
            item { Title("Overview") }
            item { Overview(overview = "After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil.") }
            item { Title("Top Billed Cast") }
        }
    }
    BackPressHandler(onBackPressed = { Router.navigateTo(Screen.HomeScreen) })
}

@Composable
fun ImageHeaderWithBackArrow() {
    Row(
        modifier = Modifier
            .background(BlueTitle)
            .padding(dimensionResource(id = R.dimen.logo_padding))
            .fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(painter = painterResource(id = R.drawable.back_arrow),
            contentDescription = "",
            modifier = Modifier.clickable(
                onClick = {
                    Router.navigateTo(Screen.HomeScreen)
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
fun MovieHeader(title : String, date : String, genre : String) {
    Box() {
        Image(painter = painterResource(id = R.drawable.ironman),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth(),
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
                fontSize = 40.sp
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
                //.width(dimensionResource(id = R.dimen.heart_height))
                //.height(dimensionResource(id = R.dimen.heart_width))
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

@Composable
fun ActorCard(
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
        //FavoriteButton(item)
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TmdbTheme {
        MovieHeader("Iron man", "05/02/2008 (US)", "Action, Science Fiction, Adventure  2h 6m")
    }
}