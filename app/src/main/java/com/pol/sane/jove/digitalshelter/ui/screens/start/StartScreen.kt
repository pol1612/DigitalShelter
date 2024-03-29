package com.pol.sane.jove.digitalshelter.ui.screens.start

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun StartScreen(navController: NavHostController) {
    var viewModel: StartScreenViewModel = viewModel()
    var uiState = viewModel.uiState.collectAsState().value
    Box {

    }
    LaunchedEffect(key1 = uiState){
        Log.i("Start::LaunchedEffect", "starts")
        viewModel.getAppInitialScreen()
        navController.popBackStack()
        navController.navigate(uiState.initialScreenRoute)
        Log.i("Start::LaunchedEffect", "ends")
    }
}