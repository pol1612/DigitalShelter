package com.pol.sane.jove.digitalshelter.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.pol.sane.jove.digitalshelter.Graph
import com.pol.sane.jove.digitalshelter.Screens

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
){
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.LOGIN_SCREEN
    ){

    }
}

object AuthScreen {
    const val LOGIN_SCREEN = "LoginScreen"
    const val SIGN_UP_SCREEN = "SignUpScreen"
    const val SIGN_UP_LOCATION_SCREEN = "SignUpLocationScreen"
}