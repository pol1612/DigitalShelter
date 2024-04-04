package com.pol.sane.jove.digitalshelter.ui.screens.main.shelter.shelter_screens.dog_list

import androidx.lifecycle.ViewModel
import com.pol.sane.jove.digitalshelter.data.interfaces.UserDetailsServiceInterface
import com.pol.sane.jove.digitalshelter.data.interfaces.UserServiceInterface
import com.pol.sane.jove.digitalshelter.ui.screens.main.shelter.shelter_screens.user_settings.ShelterUserSettingsScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ShelterDogsListScreenViewModel: ViewModel(),KoinComponent {

    private val userService: UserServiceInterface by inject()
    private val userDetailsService: UserDetailsServiceInterface by inject()

    private val _uiState = MutableStateFlow(ShelterDogsListScreenUiState())
    val uiState: StateFlow<ShelterDogsListScreenUiState> = _uiState.asStateFlow()

}