package com.pol.sane.jove.digitalshelter

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pol.sane.jove.digitalshelter.model.service.interfaces.UserDetailsServiceInterface
import com.pol.sane.jove.digitalshelter.ui.graphs.authNavGraph
import com.pol.sane.jove.digitalshelter.ui.screens.main.adopter.AdopterMainScreen
import com.pol.sane.jove.digitalshelter.ui.screens.main.shelter.ShelterMainScreen
import com.pol.sane.jove.digitalshelter.ui.theme.DigitalShelterAppTheme
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class MainActivity : ComponentActivity(), KoinComponent {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigitalShelterAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var viewModel: MainActivityViewModel = viewModel()
                    var route = ""
                    lifecycleScope.launch {

                    }
                    Log.i("MainActivity","route ${route}")
                    DigitalShelter()
                }
            }
        }
    }
}



@Composable
fun DigitalShelter(
    navController: NavHostController = rememberNavController(),
    viewModel: MainActivityViewModel = viewModel()
){
        NavHost(
            navController = navController,
            startDestination = RootGraph.AUTHENTICATION
        )
        {
            authNavGraph(navHostController = navController)
            //mainScreenNavGraph(navController = navController)
            //mainNavGraph(navController = navController)
            composable(route = RootGraph.MAIN_SHELTER){
                ShelterMainScreen(navController)
            }
            composable(route = RootGraph.MAIN_ADOPTER){
                AdopterMainScreen(navController)
            }
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
    const val MAIN_SHELTER = "main_shelter"
    const val MAIN_ADOPTER = "main_adopter"
}