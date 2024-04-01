package com.pol.sane.jove.digitalshelter.ui.screens.main.shelter

import android.util.Log
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
import com.pol.sane.jove.digitalshelter.ui.graphs.shelterMainDogsNavGraph

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
                AddBottBarNavItem(
                    selected = (uiState.selectedIcon == 0),
                    onClick = { viewModel.onDogPawIconClick(navController)
                              },
                    iconDescription = ShelterMainScreenComposables.Dogs.iconDescription,
                    iconImage = ShelterMainScreenComposables.Dogs.iconImage
                )
                AddBottBarNavItem(
                    selected = (uiState.selectedIcon == 1),
                    onClick = { viewModel.onUserIconClick(navController)
                    },
                    iconDescription = ShelterMainScreenComposables.User.iconDescription,
                    iconImage = ShelterMainScreenComposables.User.iconImage
                )

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
                shelterMainDogsNavGraph(ShelterMainScreenComposables.Dogs.route)

                composable(ShelterMainScreenComposables.User.route){

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
    var iconImage: Int
)
{
    object Dogs: ShelterMainScreenComposables(
        route = "dogs",
        iconDescription = R.string.dog_paw_icon_content_description,
        iconImage = R.drawable.paw_icon
    )
    object User: ShelterMainScreenComposables(
        route = "user",
        iconDescription = R.string.user_icon_content_description,
        iconImage = R.drawable.user_icon
    )

}

