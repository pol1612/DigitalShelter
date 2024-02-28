package com.pol.sane.jove.digitalshelter.ui.screens.auth.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.pol.sane.jove.digitalshelter.ui.graphs.AuthScreen
import org.koin.core.component.KoinComponent

class SignUpViewModel: ViewModel(), KoinComponent {
    fun goBack(navHostController: NavHostController) {
        Log.i("SignUpViewModel::goBack", "TO LOGIN SCREEN")
        navHostController.popBackStack()
        navHostController.navigate(AuthScreen.LOGIN_SCREEN)
    }

}
