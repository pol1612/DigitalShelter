package com.pol.sane.jove.digitalshelter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pol.sane.jove.digitalshelter.ui.graphs.authNavGraph
import com.pol.sane.jove.digitalshelter.ui.graphs.mainNavGraph
import com.pol.sane.jove.digitalshelter.ui.theme.DigitalShelterAppTheme
import java.security.AccessController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigitalShelterAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DigitalShelterApp()
                }
            }
        }
    }
}



@Composable
fun DigitalShelterApp(
    navController: NavHostController = rememberNavController()){
        NavHost(
            navController = navController,
            startDestination = RootGraph.AUTHENTICATION
        )
        {
            authNavGraph(navController = navController)
            mainNavGraph(navController = navController)
        }

}




@Composable
fun DigitalShelterTopBar(){

}
@Composable
fun DigitalShelterBottomBar(){

}

object RootGraph {
    const val AUTHENTICATION = "auth_graph"
    const val MAIN = "main_graph"
}