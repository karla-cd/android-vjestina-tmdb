package agency.five.tmdb

import agency.five.tmdb.ui.HomeScreen
import agency.five.tmdb.ui.Router
import agency.five.tmdb.ui.Screen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import agency.five.tmdb.ui.theme.TmdbTheme
import androidx.compose.material.ExperimentalMaterialApi

class MainActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TmdbTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    when (Router.currentScreen) {
                        Screen.HomeScreen -> HomeScreen()
                    }
                }
            }
        }
    }
}

