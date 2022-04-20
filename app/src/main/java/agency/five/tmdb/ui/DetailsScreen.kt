package agency.five.tmdb.ui

import agency.five.tmdb.R
import agency.five.tmdb.ui.theme.BlueTitle
import agency.five.tmdb.ui.theme.TmdbTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter

@Composable
fun DetailsScreen() {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        LazyColumn() {
            item { ImageHeader() }
            item { BackgroundImage() }
        }
    }
}

@Composable
fun BackgroundImage() {
    Column(
        modifier = Modifier
            .fillMaxSize()

        ) {
        Image(painter = rememberImagePainter("https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_UX128_CR0,1,128,176_AL_.jpg"), contentDescription = "")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TmdbTheme {
        BackgroundImage()
    }
}