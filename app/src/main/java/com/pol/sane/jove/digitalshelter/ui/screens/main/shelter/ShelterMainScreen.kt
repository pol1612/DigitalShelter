package com.pol.sane.jove.digitalshelter.ui.screens.main.shelter

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pol.sane.jove.digitalshelter.R
import com.pol.sane.jove.digitalshelter.ui.common.extensions.AddBottBarNavItem
import com.pol.sane.jove.digitalshelter.ui.graphs.shelterMainDogsScreensNavGraph
import com.pol.sane.jove.digitalshelter.ui.screens.main.shelter.shelter_screens.user_settings.ShelterUserSettingsScreen

@Composable
fun ShelterMainScreen(
    rootNavController: NavHostController,
    viewModel: ShelterMainViewModel = viewModel()
) {
    var navController: NavHostController = rememberNavController()

    var uiState: ShelterMainUiState = viewModel.uiState.collectAsState().value
    Scaffold(
        bottomBar = {
            BottomAppBar(
                //contentColor = MaterialTheme.colorScheme.primaryContainer,
                containerColor = MaterialTheme.colorScheme.tertiary
            ){
                var bottBarIcons: Array<ShelterMainScreenComposables> = arrayOf(
                    ShelterMainScreenComposables.Dogs,
                    ShelterMainScreenComposables.User,
                )
                bottBarIcons.forEach {
                    AddBottBarNavItem(
                        selected = (uiState.selectedIcon == it.iconNumber),
                        onClick = {
                            //viewModel.onDogPawIconClick(navController)
                            viewModel.onIconClick(
                                navController,
                                it.route,
                                it.iconNumber
                            )
                        },
                        iconDescription = it.iconDescription,
                        iconImage = it.iconImage
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .padding(paddingValues)
        ) {
            NavHost(
                navController = navController,
                startDestination = ShelterMainScreenComposables.Dogs.route,
            ){
                shelterMainDogsScreensNavGraph(navController)

                composable(ShelterMainScreenComposables.User.route){
                    ShelterUserSettingsScreen(rootNavController)
                }

            }
        }
    }
}

@Preview
@Composable
fun ShelterMainScreenPreview(){
    ShelterMainScreen(rootNavController = NavHostController(LocalContext.current))
}

sealed class ShelterMainScreenComposables(
    var route: String,
    var iconDescription: Int,
    var iconImage: Int,
    var iconNumber: Int
)
{
    object Dogs: ShelterMainScreenComposables(
        route = "dogs",
        iconDescription = R.string.dog_paw_icon_content_description,
        iconImage = R.drawable.paw_icon,
        iconNumber = 0
    )
    object User: ShelterMainScreenComposables(
        route = "user",
        iconDescription = R.string.user_icon_content_description,
        iconImage = R.drawable.user_icon,
        iconNumber = 1
    )

}

