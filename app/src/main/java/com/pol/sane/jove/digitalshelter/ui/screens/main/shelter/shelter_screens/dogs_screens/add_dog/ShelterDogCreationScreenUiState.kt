package com.pol.sane.jove.digitalshelter.ui.screens.main.shelter.shelter_screens.dogs_screens.add_dog

import android.net.Uri
import androidx.compose.material3.ExperimentalMaterial3Api
import java.time.LocalDate

data class ShelterDogCreationScreenUiState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val localUri: Uri? = null,
    val dogName: String = String(),
    val dogDescription: String = String(),
    val dogBirthDate: LocalDate = LocalDate.now(),
    val dogStatusMenuIsExpanded: Boolean = false,
    val dogStatus: String = "Available",
    val isDogAbleToBeSaved: Boolean = false,
    val dogSize: String = "Medium",
    val dogSizeMenuIsExpanded: Boolean = false,
    val isDogMale: Boolean = true
)