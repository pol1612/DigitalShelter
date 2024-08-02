package com.pol.sane.jove.digitalshelter.ui.screens.auth.signup

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.android.gms.tasks.CancellationTokenSource
import com.pol.sane.jove.digitalshelter.data.interfaces.UserServiceInterface
import com.pol.sane.jove.digitalshelter.ui.graphs.AuthScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.GeoPoint
import com.google.maps.android.compose.CameraPositionState
import com.pol.sane.jove.digitalshelter.data.database_entities.UserDetails
import com.pol.sane.jove.digitalshelter.data.interfaces.UserDetailsServiceInterface
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.apache.commons.validator.routines.EmailValidator
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SignUpViewModel: ViewModel(), KoinComponent {

    private val userService: UserServiceInterface by inject()
    private val userDetailsService: UserDetailsServiceInterface by inject()


    private val  _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()


    fun goBack(navHostController: NavHostController) {
        Log.i("SignUpViewModel::goBack", "TO LOGIN SCREEN")
        navHostController.popBackStack()
        navHostController.navigate(AuthScreen.LOGIN_SCREEN)
    }
    fun onUsernameChange(newValue: String){
        _uiState.update { it ->
            it.copy(userName = newValue)
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
                        cameraPositionState = CameraPositionState(CameraPosition
                            .fromLatLngZoom(LatLng
                                (location.latitude, location.longitude)
                                ,10f))
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
        //TODO check if passwords are the same; if false -> snackbar
        //TODO create auth user
        //TODO create userDetails with authUser id and values from the uiState
        //userDetailsService.checkIfUserNameIsTaken(_uiState.value.userName)
        Log.i("auth", "  ${userService.currentUser.email}")

        viewModelScope.launch {

            // check if userDetail with such username already exists; if true -> snackbar
            val isUseNameTaken: Boolean = userDetailsService.checkIfUserNameIsTaken(_uiState.value.userName)
            if(isUseNameTaken){
                _uiState.update {
                    it.copy(
                        snackBarText = "The username is already taken."
                    )
                }
            }
            // check if email is email if the last check (username taken) passed; if false -> snackbar
            if(_uiState.value.snackBarText.isEmpty()){
                //username not taken
                val isEmailValid = EmailValidator.getInstance().isValid(_uiState.value.email)
                if (!isEmailValid) {
                    _uiState.update {
                        it.copy(
                            snackBarText = "The email is wrongly formatted."
                        )
                    }
                }
            }
            if (_uiState.value.snackBarText.isEmpty()){
                if(!_uiState.value.password.equals(_uiState.value.repeatedPassword)){
                    _uiState.update {
                        it.copy(
                            snackBarText = "The passwords must be the same."
                        )
                    }
                }
            }
            //checks if string is alphanumerical
            if (_uiState.value.snackBarText.isEmpty()){
                if(!_uiState.value.password.matches("[a-zA-Z0-9]+".toRegex())){
                    _uiState.update {
                        it.copy(
                            snackBarText = "The password must only contain letters and numbers, not special symbols."
                        )
                    }
                }
            }
            //create auth user
            if (_uiState.value.snackBarText.isEmpty()){
                userService.createUser(
                    email = _uiState.value.email,
                    password = _uiState.value.password,
                    setSnackbarText = {text -> _uiState.update { it.copy(
                        snackBarText = text
                    ) }
                    }
                )
            }
            //create userDetails object of current auth user
            if (_uiState.value.snackBarText.isEmpty()){
                userDetailsService.createUserDetails(
                    userDetails =  UserDetails(
                        authUserId = userService.currentUser.id,
                        userName = _uiState.value.userName,
                        isUserShelter = _uiState.value.isShelter,
                        shelterLocation = GeoPoint(
                            _uiState.value.shelterLocation.latitude,
                            _uiState.value.shelterLocation.longitude
                        )
                    ),
                    setSnackbarText = { text -> _uiState.update { it.copy(snackBarText = text) } }

                )
            }

            //_uiState.update { it.copy(snackBarText = "") }

        //TODO repair snackbar the text is changed but the snackbar doesn't appear
        }

    }
    private fun checkIfTextFieldsAreNotEmptyAndEnableSignUpButton(){
        val textFieldsValues: Array<String> = arrayOf(
            _uiState.value.userName,
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

    fun setSnackbarText(newValue: String) {
        _uiState.update { it.copy(snackBarText = newValue) }
    }
}
