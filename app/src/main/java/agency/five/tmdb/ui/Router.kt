package agency.five.tmdb.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

sealed class Screen() {
    object HomeScreen : Screen()
    object FavoritesScreen : Screen()
    object DetailsScreen : Screen()
}

object Router {
    var currentScreen: Screen by mutableStateOf(Screen.HomeScreen)

    fun navigateTo(destination: Screen) {
        currentScreen = destination
    }
}