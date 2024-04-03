package com.pol.sane.jove.digitalshelter.ui.screens.main.shelter.composables.user_settings

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.pol.sane.jove.digitalshelter.R
import com.pol.sane.jove.digitalshelter.ui.common.composables.screens.UserSettingsScreen

@Composable
fun ShelterUserSettingsScreen(rootNavController: NavHostController) {

    var viewModel: ShelterUserSettingsScreenViewModel = viewModel()
    var uiState: ShelterUserSettingsScreenUiState = viewModel.uiState.collectAsState().value
    val snackbarHostState = remember { SnackbarHostState() }
    var emailSentText = stringResource(id = R.string.the_email_was_successfully_sent)
    UserSettingsScreen(
        userName = uiState.userDetails.userName!!,
        passwordRecoveryEmailSender = { viewModel.sendRecoveryEmail(emailSentText) },
        logOut = { viewModel.logOut(rootNavController) },
        deleteAccount = {},
        snackbarHostState = snackbarHostState
    )
    LaunchedEffect(key1 = uiState.userDetails.userName){
        viewModel.loadUserDetails()
    }
    LaunchedEffect(key1 = uiState.snackBarText ){
        Log.i("LaunchedEffect::snackbarText","text changed")
        if(uiState.snackBarText != ""){
            val snackbarResult =  snackbarHostState.showSnackbar(
                message = uiState.snackBarText,
                duration = SnackbarDuration.Short
            )
            if (snackbarResult == SnackbarResult.Dismissed){
                viewModel.deleteSnackBarText()
            }
        }
    }
}