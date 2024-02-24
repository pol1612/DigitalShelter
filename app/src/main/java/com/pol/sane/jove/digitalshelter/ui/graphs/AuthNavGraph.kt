package com.pol.sane.jove.digitalshelter.ui.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pol.sane.jove.digitalshelter.RootGraph
import com.pol.sane.jove.digitalshelter.ui.screens.auth.login.LoginScreen
import com.pol.sane.jove.digitalshelter.ui.screens.auth.signup.SignUpScreen

fun NavGraphBuilder.authNavGraph(
    navHostController: NavHostController
){
    navigation(
        route = RootGraph.AUTHENTICATION,
        startDestination = AuthScreen.LOGIN_SCREEN
    ){
        composable(route = AuthScreen.LOGIN_SCREEN){
            LoginScreen(navHostController = navHostController)
        }
        composable(route = AuthScreen.SIGN_UP_SCREEN){
            SignUpScreen(navHostController = navHostController)
        }

    }
}

object AuthScreen {
    const val LOGIN_SCREEN = "LoginScreen"
    const val SIGN_UP_SCREEN = "SignUpScreen"
}