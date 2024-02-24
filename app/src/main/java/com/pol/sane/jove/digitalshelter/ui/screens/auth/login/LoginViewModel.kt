package com.pol.sane.jove.digitalshelter.ui.screens.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.pol.sane.jove.digitalshelter.model.service.interfaces.UserServiceInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginViewModel: ViewModel(), KoinComponent {

    private val accountService: UserServiceInterface by inject()


    private val  _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()
    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        _uiState.update { currentState ->
            currentState.copy(
                email = newValue
            )
        }
        enableOrDisableSignInButton()
    }

    fun onPasswordChange(newValue: String) {
        _uiState.update { currentState ->
            currentState.copy(
                password = newValue)
        }
        enableOrDisableSignInButton()
    }

    fun onSignUpClick(navHostController: NavHostController) {
        runBlocking {
            Log.i("LoginViewModel::onSignUpClick", "go to signUp screen")
            //TODO: go to signup screen
        }

    }

    private fun enableOrDisableSignInButton() {
        if (!_uiState.value.email.equals("") && !_uiState.value.password.equals("")) {
            _uiState.update { currentState ->
                currentState.copy(
                    isSignInButtonEnabled = true
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    isSignInButtonEnabled = false
                )
            }
        }
    }

    fun onLoginClick(navHostController: NavHostController){
        Log.i("LoginViewModel::onLoginClick", "login viewModel method")
        //Thread.sleep(10000)
        accountService.authenticate(_uiState.value.email,_uiState.value.password)
        runBlocking {


        }
    }

    fun sendRecoveryEmail() {

    }
}