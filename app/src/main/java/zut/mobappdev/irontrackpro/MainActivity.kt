package zut.mobappdev.irontrackpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import zut.mobappdev.irontrackpro.ui.navigation.AppNavGraph
import zut.mobappdev.irontrackpro.ui.theme.IronTrackProTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IronTrackProTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Створюємо контролер навігації
                    val navController = rememberNavController()

                    // Передаємо його в наш граф
                    AppNavGraph(navController = navController)
                }
            }
        }
    }
}