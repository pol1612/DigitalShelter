package com.pol.sane.jove.digitalshelter.ui.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pol.sane.jove.digitalshelter.ui.screens.main.shelter.ShelterMainScreenComposables
import com.pol.sane.jove.digitalshelter.ui.screens.main.shelter.shelter_screens.dog_list.ShelterDogsListScreen

fun NavGraphBuilder.shelterMainDogsNavGraph(navHostController: NavHostController) {
    navigation(
        route = ShelterMainScreenComposables.Dogs.route,
        startDestination = ShelterMainDogsScreenComposables.DOGS_LIST_SCREEN
    ){
        composable(route = ShelterMainDogsScreenComposables.DOGS_LIST_SCREEN){
            ShelterDogsListScreen(navHostController)
        }
        composable(route = ShelterMainDogsScreenComposables.DOG_CREATION_SCREEN){

        }
        composable(route = ShelterMainDogsScreenComposables.DOG_DETAILS_SCREEN){

        }
        composable(route = ShelterMainDogsScreenComposables.DOG_UPDATE_SCREEN){

        }
    }
}

object ShelterMainDogsScreenComposables {
    const val DOGS_LIST_SCREEN = "ListScreen"
    const val DOG_CREATION_SCREEN = "DogCreationScreen"
    const val DOG_DETAILS_SCREEN = "DogDetailsScreen"
    const val DOG_UPDATE_SCREEN = "DogUpdateScreen"
}