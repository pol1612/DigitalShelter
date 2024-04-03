package com.pol.sane.jove.digitalshelter.ui.screens.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.pol.sane.jove.digitalshelter.data.interfaces.UserDetailsServiceInterface
import com.pol.sane.jove.digitalshelter.data.interfaces.UserServiceInterface
import com.pol.sane.jove.digitalshelter.ui.RootGraph
import com.pol.sane.jove.digitalshelter.ui.graphs.AuthScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.apache.commons.validator.routines.EmailValidator
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginViewModel: ViewModel(), KoinComponent {

    private val userService: UserServiceInterface by inject()
    private val userDetailsService: UserDetailsServiceInterface by inject()


    private val  _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()


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

    fun deleteSnackBarText(){
        _uiState.update { currentState ->
            currentState.copy(
                snackBarText = ""
            )
        }
    }

    fun onLoginClick(navHostController: NavHostController){
        viewModelScope.launch {
            Log.i("LoginViewModel::onLoginClick", "login viewModel method")
            //Thread.sleep(10000)
            userService.authenticateUser(
                _uiState.value.email,
                _uiState.value.password
            ) {
                text ->
                Log.i("setViewModelSnackbarText","snackbar text changed")
                _uiState.update { it ->
                    it.copy(
                        snackBarText = text
                    )
                }
            }
            Log.i("LoginViewModel::onLoginClick::snacktextAfterAuth", "${_uiState.value.snackBarText}")
            if (_uiState.value.snackBarText.isNullOrEmpty()){
                val currUsUserDetails = userDetailsService.getCurrentUserUserDetails()

                Log.i("onLoginClick::currentUserUserDetails:isShelter",
                    "${currUsUserDetails?.isUserShelter}")
                if(currUsUserDetails != null){
                    if (currUsUserDetails.isUserShelter == true){
                        navHostController.popBackStack()
                        navHostController.navigate(RootGraph.MAIN_SHELTER)
                    }else{
                        navHostController.popBackStack()
                        navHostController.navigate(RootGraph.MAIN_ADOPTER)

                    }
                }
            }

        }

    }

    fun sendRecoveryEmail(emailSentText: String) {
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
            if(_uiState.value.snackBarText.isNullOrEmpty()){
                _uiState.update { it ->
                    it.copy(
                        snackBarText = emailSentText
                    )
                }
            }
        }

    }
}