package com.pol.sane.jove.digitalshelter.ui.screens.main.shelter.shelter_screens.dog_list

import com.pol.sane.jove.digitalshelter.data.pojo.Dog

data class ShelterDogsListScreenUiState(
    var dogsList: List<Dog> = listOf()
)