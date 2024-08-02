package com.pol.sane.jove.digitalshelter.ui.screens.main.shelter.shelter_screens.dogs_screens

import com.pol.sane.jove.digitalshelter.data.database_entities.Dog

data class ShelterDogsScreensUiState(
    var dogsList: HashMap<String,Dog> = hashMapOf()
)