package com.pol.sane.jove.digitalshelter.ui.screens.main.shelter.shelter_screens.dogs_screens

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.pol.sane.jove.digitalshelter.data.interfaces.UserDetailsServiceInterface
import com.pol.sane.jove.digitalshelter.data.interfaces.UserServiceInterface
import com.pol.sane.jove.digitalshelter.ui.graphs.ShelterMainDogsScreens
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ShelterDogsScreensViewModel: ViewModel(),KoinComponent {



    private val userService: UserServiceInterface by inject()
    private val userDetailsService: UserDetailsServiceInterface by inject()

    private val _uiState = MutableStateFlow(ShelterDogsScreensUiState())
    val uiState: StateFlow<ShelterDogsScreensUiState> = _uiState.asStateFlow()

    fun createDogButtonOnClick(navHostController: NavHostController) {
        navHostController.navigate(ShelterMainDogsScreens.DOG_CREATION_SCREEN)
    }
}