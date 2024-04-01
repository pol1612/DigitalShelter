package com.pol.sane.jove.digitalshelter.ui.screens.main.shelter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.pol.sane.jove.digitalshelter.ui.screens.auth.login.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

class ShelterMainViewModel: ViewModel(), KoinComponent{

    private val  _uiState = MutableStateFlow(ShelterMainUiState())
    val uiState: StateFlow<ShelterMainUiState> = _uiState.asStateFlow()
    fun onDogPawIconClick(navController: NavHostController) {
        Log.i("onDogPawIconClick","start")
        if(_uiState.value.selectedIcon != 0){
            navController.navigate(ShelterMainScreenComposables.Dogs.route)
            _uiState.update { it.copy(selectedIcon = 0) }
            Log.i("onDogPawIconClick", "Went from dog paw icon and dog list screen to user icon and user screen")
        }
    }

    fun onUserIconClick(navController: NavHostController) {
        Log.i("onUserIconClick","start")
        if(_uiState.value.selectedIcon != 1){
            navController.navigate(ShelterMainScreenComposables.User.route)
            _uiState.update { it.copy(selectedIcon = 1) }
            Log.i("onUserIconClick", "Went from user icon and user screen to dog paw icon and dog list screen")

        }
    }

}