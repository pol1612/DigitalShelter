package com.pol.sane.jove.digitalshelter.ui.screens.main.shelter.composables.user_settings

import com.pol.sane.jove.digitalshelter.model.service.UserDetails

data class ShelterUserSettingsScreenUiState(
    var userDetails: UserDetails = UserDetails(),
    var snackBarText: String = String()
)