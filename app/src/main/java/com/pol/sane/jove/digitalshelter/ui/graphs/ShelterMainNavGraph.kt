package com.pol.sane.jove.digitalshelter.ui.graphs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pol.sane.jove.digitalshelter.ui.screens.main.shelter.ShelterMainScreenComposables

fun NavGraphBuilder.shelterMainDogsNavGraph(dogs: String) {
    navigation(
        route = ShelterMainScreenComposables.Dogs.route,
        startDestination = ShelterMainDogsScreen.LIST_SCREEN
    ){
        composable(route = ShelterMainDogsScreen.LIST_SCREEN){
            Scaffold { paddingValues ->
                Column(
                    modifier = Modifier
                    .padding(paddingValues)
                ){

                }
            }
        }
    }
}

object ShelterMainDogsScreen {
    const val LIST_SCREEN = "ListScreen"
}