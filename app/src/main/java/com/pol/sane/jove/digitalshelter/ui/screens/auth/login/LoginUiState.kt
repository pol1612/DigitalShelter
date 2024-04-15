package com.pol.sane.jove.digitalshelter.ui.screens.auth.login

import androidx.compose.material3.SnackbarData

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isSignInButtonEnabled: Boolean = false,
    val snackBarText: String = ""
)

