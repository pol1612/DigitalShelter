package com.pol.sane.jove.digitalshelter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.pol.sane.jove.digitalshelter.ui.graphs.authNavGraph
import com.pol.sane.jove.digitalshelter.ui.graphs.mainNavGraph
import com.pol.sane.jove.digitalshelter.ui.theme.DigitalShelterAppTheme


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
                    DigitalShelter()
                }
            }
        }
    }
}



@Composable
fun DigitalShelter(
    navController: NavHostController = rememberNavController()){
        NavHost(
            navController = navController,
            startDestination = RootGraph.AUTHENTICATION
        )
        {
            authNavGraph(navHostController = navController)
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