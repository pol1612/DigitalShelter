package com.pol.sane.jove.digitalshelter.ui.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pol.sane.jove.digitalshelter.RootGraph
import com.pol.sane.jove.digitalshelter.Screens
import com.pol.sane.jove.digitalshelter.ui.screens.auth.login.LoginScreen
import com.pol.sane.jove.digitalshelter.ui.screens.auth.signup.SignUpScreen
import com.pol.sane.jove.digitalshelter.ui.screens.auth.signuplocation.SignUpLocationScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
){
    navigation(
        route = RootGraph.AUTHENTICATION,
        startDestination = AuthScreen.LOGIN_SCREEN
    ){
        composable(route = AuthScreen.LOGIN_SCREEN){
            LoginScreen()
        }
        composable(route = AuthScreen.SIGN_UP_SCREEN){
            SignUpScreen()
        }
        composable(route = AuthScreen.SIGN_UP_LOCATION_SCREEN){
            SignUpLocationScreen()
        }
    }
}

object AuthScreen {
    const val LOGIN_SCREEN = "LoginScreen"
    const val SIGN_UP_SCREEN = "SignUpScreen"
    const val SIGN_UP_LOCATION_SCREEN = "SignUpLocationScreen"
}