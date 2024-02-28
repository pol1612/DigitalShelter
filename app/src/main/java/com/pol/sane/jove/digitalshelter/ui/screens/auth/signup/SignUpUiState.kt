package com.pol.sane.jove.digitalshelter.ui.screens.auth.signup

import com.google.android.gms.maps.model.LatLng

data class SignUpUiState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val repeatedPassword: String = "",
    val isShelter: Boolean = false,
    val cameraLocation: LatLng = LatLng(0.0,0.0),
    val shelterLocation: LatLng = LatLng(0.0,0.0),
){

}
