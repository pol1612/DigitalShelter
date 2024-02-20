package com.pol.sane.jove.digitalshelter.ui.screens.auth.login

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {
    private val  _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()


    fun onSignUpClick(navHostController: NavHostController) {

    }

}