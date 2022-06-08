package agency.five.tmdb

import agency.five.tmdb.ui.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import agency.five.tmdb.ui.theme.TmdbTheme
import agency.five.tmdb.vm.FavoritesViewModel
import agency.five.tmdb.vm.HomeViewModel
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.viewModel

class MainActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TmdbTheme {

                val homeViewModel : HomeViewModel = getViewModel()
                val favoritesViewModel : FavoritesViewModel by viewModel()

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "homeScreen") {
                    composable("homeScreen") { HomeScreen(navController, homeViewModel) }
                    composable("favoritesScreen") { FavoritesScreen(navController, favoritesViewModel) }
                    composable(
                        "homeScreen/{movieId}",
                        arguments = listOf(navArgument("movieId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        DetailsScreen(navController, homeViewModel, backStackEntry.arguments!!.getInt("movieId"))
                    }
                }


                /*
                Surface(color = MaterialTheme.colors.background) {
                    when (Router.currentScreen) {
                        Screen.HomeScreen -> HomeScreen(homeViewModel)
                        Screen.FavoritesScreen -> FavoritesScreen(favoritesViewModel)
                        Screen.DetailsScreen -> DetailsScreen()
                    }
                }*/
            }
        }
    }
}

