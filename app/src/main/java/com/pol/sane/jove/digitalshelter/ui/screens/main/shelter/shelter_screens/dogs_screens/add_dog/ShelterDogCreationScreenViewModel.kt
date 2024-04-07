package com.pol.sane.jove.digitalshelter.ui.screens.main.shelter.shelter_screens.dogs_screens.add_dog

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import java.time.LocalDate

class ShelterDogCreationScreenViewModel: ViewModel(),KoinComponent {

    private val _uiState = MutableStateFlow(ShelterDogCreationScreenUiState())
    val uiState: StateFlow<ShelterDogCreationScreenUiState> = _uiState.asStateFlow()

    fun onDogNameChange(newValue: String){
        _uiState.update {
            it.copy(
                dogName = newValue
            )
        }
    }
    fun onCloseIconClick(navHostController: NavHostController) {

    }

    fun onSaveIconClick(navHostController: NavHostController) {

    }

    fun updateLocalUri(localUri: Uri?) {
        _uiState.update {
            it.copy(
                localUri = localUri
            )
        }
    }

    fun onDogDescriptionChange(newValue: String) {
        _uiState.update { it.copy(
            dogDescription = newValue
        ) }
    }

    fun updateDogBirthDate(dogBirthDate: LocalDate) {
        _uiState.update {
            it.copy(
                dogBirthDate = dogBirthDate
            )
        }
    }
}