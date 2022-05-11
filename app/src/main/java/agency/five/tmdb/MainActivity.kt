package agency.five.tmdb

import agency.five.tmdb.ui.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import agency.five.tmdb.ui.theme.TmdbTheme
import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.ViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.viewModel
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TmdbTheme {
                val homeViewModel : HomeViewModel = getViewModel()
                val favoritesViewModel : FavoritesViewModel by viewModel()
                Surface(color = MaterialTheme.colors.background) {
                    when (Router.currentScreen) {
                        Screen.HomeScreen -> HomeScreen(homeViewModel)
                        Screen.FavoritesScreen -> FavoritesScreen(favoritesViewModel)
                        Screen.DetailsScreen -> DetailsScreen()
                    }
                }
            }
        }
    }
}

