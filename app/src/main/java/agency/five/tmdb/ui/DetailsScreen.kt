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
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun DetailsScreen() {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        LazyColumn() {
            item { ImageHeaderWithBackArrow() }
            item { MovieHeader("Iron man (2008)", "05/02/2008 (US)", "Action, Science Fiction, Adventure  2h 6m") }
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TmdbTheme {
        MovieHeader("Iron man", "05/02/2008 (US)", "Action, Science Fiction, Adventure  2h 6m")
    }
}