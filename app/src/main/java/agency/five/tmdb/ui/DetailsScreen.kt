package agency.five.tmdb.ui

import agency.five.tmdb.R
import agency.five.tmdb.ui.theme.BlueTitle
import agency.five.tmdb.ui.theme.TmdbTheme
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
    
    val productionMembers : List<ProductionMember> = listOf(
        ProductionMember(
            id = 1, 
            name = "Don Heck", 
            role = "Characters"
        ),
        ProductionMember(
            id = 2,
            name = "Jack Kirby",
            role = "Characters"
        ),
        ProductionMember(
            id = 3,
            name = "Jon Favreau",
            role = "Director"
        ),
        ProductionMember(
            id = 4,
            name = "Don Heck",
            role = "Screenplay"
        ),
        ProductionMember(
            id = 1,
            name = "Jack Marcum",
            role = "Screenplay"
        ),
        ProductionMember(
            id = 1,
            name = "Matt Holloway",
            role = "Screenplay"
        )
    )

    val actors : List<Actor> = listOf(
        Actor(
            id = 1,
            name = "Robert Downey Jr.",
            role = "Tony Stark/Iron Man",
            imageUrl = "https://s3-alpha-sig.figma.com/img/3238/5dd8/9c76f8ac0050ce88bfe0da9a9711f821?Expires=1652054400&Signature=e~vLgZs7KA9y2-bOSrtknhOI0R-i-ksKT82JqocCOqI4hWiowNy7M5qZWq3liahO-2cWn32uti9l6lGjpUiH84j-23IdFk6vZ0bp3Vj00gF~VwWtLWzPmTf4UiBAzN3bBfOy0Bj50DlmufHOCLqVpxjgE8BB12BQC~EWTw5h62cJwpg7SyIwuIzrkvP5RlH8cETa3eMs7wYh7U1iU2StLJqw5Fpt6xvwlXoHWekrqCwgXHQhDgp3uD-0mvqkkIdCPrK47VJSiQpBKjwotHWuG2xkFgE0YfH3Y-58eExkKMxT8YjdLoiCWf7rBsIDqtTZdWEe-WsIqOl~rPt7a7MTzQ__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA"
        ),
        Actor(
            id = 2,
            name = "Terrence Howard",
            role = "James Rhodes",
            imageUrl = "https://s3-alpha-sig.figma.com/img/9b12/0d3e/20964d32c3bf159aa7658791385a8b53?Expires=1652054400&Signature=Ad~5es9Efx1ZhHHeEwHDgFV-qeAbZkgQLZZzIKR8nb3LwvGzyql5BawFqzW92Mb5DZH6ad5M1QCY3pAE15vIY79MxHwa0tAnPbk0MzbqnH~cRTtNFYYGhRMS0omSCGaa2cy4Z5X6L9yzTm7cEnddr1rGbODUV3GUCsBARNTpPxb0nCy61pb5qRBpFrwk45wgtwcY7O1~FN5A6fmBUyfZlYAWuf8jFcDWb4le5TgHcadBPH6OuUNBcGusMPDBUfXmNh7fXg0DYBx2eLsS47UOocvpqHA6xfml7P5ClrzNOQ1-FTwoIl-4hfGwNqiRoYOGYN2b7tjw3gaAFuT14-gXjQ__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA"

        ),
        Actor(
            id = 3,
            name = "Jeff Bridges",
            role = "Obadiah Stane / Iron Monger",
            imageUrl = "https://s3-alpha-sig.figma.com/img/2cf4/82d7/530de657caa0f36c35f3b776c09ea221?Expires=1652054400&Signature=FIFOTsBxdDv9fv-UthcJO0zTAAwQJxnRLI1dIrxUmk0skwRseNkPtPH9LT2q2uhLB~-D~R0RzOwNv9JSSeZothJkjI4Clo3GtieZwgqfaWRmI~VvxOcKF~v5mB1hXCdx~mRt9SzQWyfY33eQqi0YtYQwiCHawvRkqW~BDY0G5zXyQSvc0Lgl1ViJNRT74CqdKY4~M0vMZE2YdWAhPhJ4Yc3t0wGmIsP3EPRQ39DcyhBw3uk0zCWbZqMnL31mPiIBwn2jcfrSXq5r2GpUK81JOtE0hGgknhL3vgkjn~CaRbU2FcMdIOEMaHcJxuHHKHMVbGwBw4uwX58DpudvPWiCXg__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA"
        )
    )
    
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { ImageHeaderWithBackArrow() },
    ) {
        LazyColumn() {
            item { MovieHeader("Iron man (2008)", "05/02/2008 (US)", "Action, Science Fiction, Adventure  2h 6m") }
            item { Title("Overview") }
            item { Overview(overview = "After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil.") }
            item { ProductionMembers(productionMembers = productionMembers) }
            item { Title("Top Billed Cast") }
            item { Actors(actors = actors) }
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
            .fillMaxWidth(),
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

data class ProductionMember(
    val id : Int, 
    val name : String, 
    val role : String
)

@Composable
fun ProductionMembers(productionMembers : List<ProductionMember>) {
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
                    text = it.role,
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
fun ActorCard(modifier : Modifier, actor : Actor) {
    Box(
        modifier = modifier.shadow(2.dp),
    ) {
        Image(
            painter = rememberImagePainter(actor.imageUrl),
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
                    text = actor.role,
                    modifier = Modifier.width(width = dimensionResource(id = R.dimen.movie_card_width)),
                    style = MaterialTheme.typography.body2,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun Actors(actors : List<Actor>) {
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
