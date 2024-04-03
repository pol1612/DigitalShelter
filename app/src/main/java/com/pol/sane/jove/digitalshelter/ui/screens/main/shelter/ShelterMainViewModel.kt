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
    fun onIconClick(navController: NavHostController, route: String, iconNumber: Int){
        if(_uiState.value.selectedIcon != iconNumber){
            navController.popBackStack()
            navController.navigate(route)
            _uiState.update { it.copy(selectedIcon = iconNumber) }
        }
    }

}