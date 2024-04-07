package com.pol.sane.jove.digitalshelter.ui.graphs

import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pol.sane.jove.digitalshelter.ui.screens.main.shelter.ShelterMainScreenComposables
import com.pol.sane.jove.digitalshelter.ui.screens.main.shelter.shelter_screens.dogs_screens.add_dog.ShelterDogCreationScreen
import com.pol.sane.jove.digitalshelter.ui.screens.main.shelter.shelter_screens.dogs_screens.dog_list.ShelterDogsListScreen

fun NavGraphBuilder.shelterMainDogsScreensNavGraph(navHostController: NavHostController) {
    navigation(
        route = ShelterMainScreenComposables.Dogs.route,
        startDestination = ShelterMainDogsScreens.DOGS_LIST_SCREEN
    ){
        composable(route = ShelterMainDogsScreens.DOGS_LIST_SCREEN){
            ShelterDogsListScreen(navHostController)
        }
        composable(route = ShelterMainDogsScreens.DOG_CREATION_SCREEN){
            ShelterDogCreationScreen(navHostController)

        }
        composable(route = ShelterMainDogsScreens.DOG_DETAILS_SCREEN){
            it.arguments?.getString("id")


        }
        composable(route = ShelterMainDogsScreens.DOG_UPDATE_SCREEN){

        }
    }
}

object ShelterMainDogsScreens {
    const val DOGS_LIST_SCREEN = "ListScreen"
    const val DOG_CREATION_SCREEN = "DogCreationScreen"
    const val DOG_DETAILS_SCREEN = "DogDetailsScreen/"
    const val DOG_UPDATE_SCREEN = "DogUpdateScreen"
}