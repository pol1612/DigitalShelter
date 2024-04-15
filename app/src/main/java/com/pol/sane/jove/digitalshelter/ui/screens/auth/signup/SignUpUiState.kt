package com.pol.sane.jove.digitalshelter.ui.screens.auth.signup

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState

data class SignUpUiState(
    val userName: String = "",
    val email: String = "",
    val password: String = "",
    val repeatedPassword: String = "",
    val snackBarText: String = "",
    val isSignUpButtonEnabled: Boolean = false,
    val isShelter: Boolean = false,
    val hasShelterLocationMarkerBeenPlacedOnce: Boolean = false,
    val cameraPositionState: CameraPositionState = CameraPositionState(CameraPosition.fromLatLngZoom(LatLng( 40.730610, -73.935242),10f)),
    val shelterLocation: LatLng = LatLng(0.0,0.0),

    )
