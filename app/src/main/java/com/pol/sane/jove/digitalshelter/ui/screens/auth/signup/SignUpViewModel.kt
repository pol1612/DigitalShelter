package com.pol.sane.jove.digitalshelter.ui.screens.auth.signup

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.android.gms.tasks.CancellationTokenSource
import com.pol.sane.jove.digitalshelter.model.service.implementations.UserDetailsService
import com.pol.sane.jove.digitalshelter.model.service.interfaces.UserServiceInterface
import com.pol.sane.jove.digitalshelter.ui.graphs.AuthScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
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
        checkIfTextFieldsAreNotEmptyAndEnableSignUpButton()
    }
    fun onEmailChange(newValue: String){
        _uiState.update { it ->
            it.copy(email = newValue)
        }
        checkIfTextFieldsAreNotEmptyAndEnableSignUpButton()
    }
    fun onPasswordChange(newValue: String){
        _uiState.update { it ->
            it.copy(password = newValue)
        }
        checkIfTextFieldsAreNotEmptyAndEnableSignUpButton()
    }
    fun onRepeatedPasswordChange(newValue: String){
        _uiState.update { it ->
            it.copy(repeatedPassword = newValue)
        }
        checkIfTextFieldsAreNotEmptyAndEnableSignUpButton()
    }
    @OptIn(ExperimentalPermissionsApi::class)
    fun onIsShelterChange(
        newValue: Boolean,
        locationPermissionsState: MultiplePermissionsState,
        context: Context
    ){


        _uiState.update {
            it.copy(isShelter = newValue)
        }

        val allPermissionsRevoked =
            locationPermissionsState.permissions.size ==
                    locationPermissionsState.revokedPermissions.size
        if(allPermissionsRevoked){
            locationPermissionsState.launchMultiplePermissionRequest()

        }
        checkIfTextFieldsAreNotEmptyAndEnableSignUpButton()
        //makeCurrentLocationCameraLocation(context)  // TODO

    }
    @SuppressLint("MissingPermission")
    fun makeCurrentLocationCameraLocation(current: Context) {
        val fusedLocationClient = LocationServices
            .getFusedLocationProviderClient(current)
        Log.i("makeCurrentLocationCameraLocation", "loc not found yet")
        fusedLocationClient.getCurrentLocation(
            PRIORITY_HIGH_ACCURACY,
            CancellationTokenSource().token
        )
            .addOnSuccessListener {location: Location ->
                Log.i("makeCurrentLocationCameraLocation", "loc found")
                _uiState.update {
                    it.copy(
                        cameraPositionState = CameraPositionState(CameraPosition.fromLatLngZoom(LatLng(location.latitude, location.longitude),10f))
                    )
                }
            }
    }

    fun onMapClick(newShelterLocation: LatLng) {
        Log.i("SignUpViewModel::onMapClick","new shelter location")
        if (!_uiState.value.hasShelterLocationMarkerBeenPlacedOnce){
            _uiState.update {
                it.copy( hasShelterLocationMarkerBeenPlacedOnce = true)
            }
        }
        _uiState.update {
            it.copy( shelterLocation = newShelterLocation)
        }
        checkIfTextFieldsAreNotEmptyAndEnableSignUpButton()
    }

    fun onSignUpClick() {

    }
    private fun checkIfTextFieldsAreNotEmptyAndEnableSignUpButton(){
        val textFieldsValues: Array<String> = arrayOf(
            _uiState.value.username,
            _uiState.value.email,
            _uiState.value.password,
            _uiState.value.repeatedPassword
        )
        var shouldSignUpButtonBeEnabled = textFieldsValues.all { it.isNotBlank() }
        if (_uiState.value.isShelter){
            shouldSignUpButtonBeEnabled = _uiState.value.hasShelterLocationMarkerBeenPlacedOnce && shouldSignUpButtonBeEnabled
        }
        _uiState.update {
            it.copy(
                isSignUpButtonEnabled = shouldSignUpButtonBeEnabled
            )
        }
    }
}
