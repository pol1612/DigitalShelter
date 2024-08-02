package com.pol.sane.jove.digitalshelter.ui.screens.main.shelter.shelter_screens.user_settings

import com.pol.sane.jove.digitalshelter.data.database_entities.UserDetails

data class ShelterUserSettingsScreenUiState(
    var userDetails: UserDetails = UserDetails(),
    var snackBarText: String = String()
)