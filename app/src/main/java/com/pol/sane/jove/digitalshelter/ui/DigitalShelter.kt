package com.pol.sane.jove.digitalshelter.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pol.sane.jove.digitalshelter.ui.graphs.authNavGraph
import com.pol.sane.jove.digitalshelter.ui.screens.main.adopter.AdopterMainScreen
import com.pol.sane.jove.digitalshelter.ui.screens.main.shelter.ShelterMainScreen
import com.pol.sane.jove.digitalshelter.ui.screens.start.StartScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DigitalShelter(
    navController: NavHostController = rememberNavController(),
){

    NavHost(
        navController = navController,
        startDestination = RootGraph.START
    )
    {
        composable(route = RootGraph.START) {
            StartScreen(navController)
        }
        authNavGraph(navHostController = navController)
        //mainScreenNavGraph(navController = navController)
        //mainNavGraph(navController = navController)
        composable(route = RootGraph.MAIN_SHELTER) {
            ShelterMainScreen(navController)
        }
        composable(route = RootGraph.MAIN_ADOPTER) {
            AdopterMainScreen(navController)
        }
    }
}

object RootGraph {

    const val START = "start"
    const val AUTHENTICATION = "auth_graph"
    const val MAIN_SHELTER = "main_shelter"
    const val MAIN_ADOPTER = "main_adopter"
}