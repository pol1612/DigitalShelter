package com.pol.sane.jove.digitalshelter.ui.screens.main.shelter.shelter_screens.user_settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.pol.sane.jove.digitalshelter.data.interfaces.UserDetailsServiceInterface
import com.pol.sane.jove.digitalshelter.data.interfaces.UserServiceInterface
import com.pol.sane.jove.digitalshelter.ui.RootGraph
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.apache.commons.validator.routines.EmailValidator
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ShelterUserSettingsScreenViewModel: ViewModel(), KoinComponent {

    private val userService: UserServiceInterface by inject()
    private val userDetailsService: UserDetailsServiceInterface by inject()


    private val _uiState = MutableStateFlow(ShelterUserSettingsScreenUiState())
    val uiState: StateFlow<ShelterUserSettingsScreenUiState> = _uiState.asStateFlow()

    suspend fun loadUserDetails() {
        var userDetails = userDetailsService.getCurrentUserUserDetails()
        if (userDetails != null) {
            _uiState.update { it.copy(userDetails = userDetails) }
        }
    }

    fun sendRecoveryEmail(emailSentText: String) {
        Log.i("sendRecoveryEmail", "start")
        viewModelScope.launch {
            _uiState.update { it -> it.copy(snackBarText = "") }
            if (EmailValidator.getInstance().isValid(userService.currentUser.email)) {
                Log.i("sendRecoveryEmail", "email is valid + ${userService.currentUser.email}")
                userService.sendRecoveryEmail(userService.currentUser.email!!) { text ->
                    _uiState.update { it ->
                        it.copy(
                            snackBarText = text
                        )
                    }
                }

            } else {
                Log.i("sendRecoveryEmail", "email is not valid")
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

    fun deleteSnackBarText() {
        _uiState.update { currentState ->
            currentState.copy(
                snackBarText = ""
            )
        }

    }

    fun logOut(rootNavController: NavHostController) {
        viewModelScope.launch {
            userService.signOut()
            rootNavController.popBackStack()
            rootNavController.navigate(RootGraph.AUTHENTICATION)
        }
    }

    fun deleteAccountAndItsDogs(){
        //TODO: Delete all the images of the dogs
        //TODO: Delete all the saved instances of each dog
        //TODO: Delete all the dogs of the user
        //TODO: Delete the userDetails of the user
        //TODO: Delete the auth user
        //TODO: Log out the app
        //TODO: go to Login screen

    }
}