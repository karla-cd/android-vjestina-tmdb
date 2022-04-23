package agency.five.tmdb

import agency.five.tmdb.ui.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import agency.five.tmdb.ui.theme.TmdbTheme
import androidx.compose.material.ExperimentalMaterialApi
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TmdbTheme {
                startKoin {
                    modules(moviesViewModelModule, moviesViewModelModule)
                }
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    when (Router.currentScreen) {
                        Screen.HomeScreen -> HomeScreen()
                        Screen.FavoritesScreen -> FavoritesScreen()
                        Screen.DetailsScreen -> DetailsScreen()
                    }
                }
            }
        }
    }
}

