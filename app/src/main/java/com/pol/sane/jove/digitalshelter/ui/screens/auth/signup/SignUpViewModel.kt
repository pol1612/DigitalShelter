package com.pol.sane.jove.digitalshelter.ui.screens.auth.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.pol.sane.jove.digitalshelter.model.service.implementations.UserDetailsService
import com.pol.sane.jove.digitalshelter.model.service.interfaces.UserServiceInterface
import com.pol.sane.jove.digitalshelter.ui.graphs.AuthScreen
import com.pol.sane.jove.digitalshelter.ui.screens.auth.login.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SignUpViewModel: ViewModel(), KoinComponent {

    private val userService: UserServiceInterface by inject()
    private val userDetailsService: UserDetailsService by inject()


    private val  _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()


    fun goBack(navHostController: NavHostController) {
        Log.i("SignUpViewModel::goBack", "TO LOGIN SCREEN")
        navHostController.popBackStack()
        navHostController.navigate(AuthScreen.LOGIN_SCREEN)
    }
    fun onUsernameChange(newValue: String){
        _uiState.update { it ->
            it.copy(username = newValue)
        }
    }
    fun onEmailChange(newValue: String){
        _uiState.update { it ->
            it.copy(email = newValue)
        }
    }
    fun onPasswordChange(newValue: String){
        _uiState.update { it ->
            it.copy(password = newValue)
        }
    }
    fun onRepeatedPasswordChange(newValue: String){
        _uiState.update { it ->
            it.copy(repeatedPassword = newValue)
        }
    }
    fun onIsShelterChange(newValue: Boolean){
        _uiState.update { it ->
            it.copy(isShelter = newValue)
        }
    }
}
