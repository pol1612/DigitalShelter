package com.pol.sane.jove.digitalshelter.ui.screens.main.shelter.shelter_screens.dogs_screens

import com.pol.sane.jove.digitalshelter.data.pojo.Dog

data class ShelterDogsScreensUiState(
    var dogsList: HashMap<String,Dog> = hashMapOf()
)