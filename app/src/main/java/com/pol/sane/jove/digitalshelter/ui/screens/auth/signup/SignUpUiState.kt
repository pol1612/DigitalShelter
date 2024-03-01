package com.pol.sane.jove.digitalshelter.ui.screens.auth.signup

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState

data class SignUpUiState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val repeatedPassword: String = "",
    val isShelter: Boolean = false,
    val hasShelterLocationMarkerBeenPlaced: Boolean = false,
    val cameraPositionState: CameraPositionState = CameraPositionState(CameraPosition.fromLatLngZoom(LatLng(51.508610, -0.163611),10f)),
    val shelterLocation: LatLng = LatLng(0.0,0.0),

    )
