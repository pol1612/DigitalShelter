package com.pol.sane.jove.digitalshelter.ui.screens.auth.login

import android.util.Log
import android.widget.Toast
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.google.rpc.context.AttributeContext.Auth
import com.pol.sane.jove.digitalshelter.model.service.interfaces.UserServiceInterface
import com.pol.sane.jove.digitalshelter.ui.graphs.AuthScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.apache.commons.validator.routines.EmailValidator
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginViewModel: ViewModel(), KoinComponent {

    private val userService: UserServiceInterface by inject()


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

        Log.i("LoginViewModel::onSignUpClick", "go to signUp screen")
        //TODO: go to signup screen

        navHostController.navigate(AuthScreen.SIGN_UP_SCREEN)

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
        userService.authenticate(
            _uiState.value.email,
            _uiState.value.password
        ) { text ->
            _uiState.update { it ->
                it.copy(
                    snackBarText = text
                )
            }
        }
        _uiState.update { it ->
            it.copy(
                snackBarText = ""
            )
        }
        //TODO: load user details and go to shelter or adopter accordingly

    }

    fun sendRecoveryEmail() {
        viewModelScope.launch {
            _uiState.update { it -> it.copy(snackBarText = "") }
            if(EmailValidator.getInstance().isValid(_uiState.value.email)){
                 userService.sendRecoveryEmail(_uiState.value.email) { text ->
                    _uiState.update { it ->
                        it.copy(
                            snackBarText = text
                        )
                    }
                }
            }
            else{
                _uiState.update { currentState ->
                    currentState.copy(
                        snackBarText = "The email is wrongly formatted."
                    )
                }
            }
            _uiState.update { currentState ->
                currentState.copy(
                    email = ""
                )
            }

        }

    }
}