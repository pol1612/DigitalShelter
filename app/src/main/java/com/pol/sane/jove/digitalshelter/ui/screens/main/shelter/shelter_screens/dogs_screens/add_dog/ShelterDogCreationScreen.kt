package com.pol.sane.jove.digitalshelter.ui.screens.main.shelter.shelter_screens.dogs_screens.add_dog

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pol.sane.jove.digitalshelter.R
import com.pol.sane.jove.digitalshelter.ui.common.composables.screens.ShelterDogDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShelterDogCreationScreen(
    navHostController: NavHostController,
    viewModel: ShelterDogCreationScreenViewModel = viewModel()
){
    var uiState = viewModel.uiState.collectAsState().value
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.user_settings)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                actions = {
                    IconButton(onClick = { viewModel.onCloseIconClick(navHostController) }) {
                        Icon(Icons.Default.Close,
                            stringResource(R.string.x_icon_it_closes_the_dog_creation_screen_without_saving_the_dog))
                    }
                    Spacer(modifier = Modifier
                        .width(20.dp)
                    )
                    IconButton(onClick = { viewModel.onSaveIconClick(navHostController) }) {
                        Icon(Icons.Default.Done,
                            stringResource(R.string.tick_icon_it_saves_the_dog_into_the_app_and_goes_back_to_the_previous_screen_it)
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it),


        ) {
            val singlePhotoPicker = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.PickVisualMedia(),
                onResult = {
                    viewModel.updateLocalUri(it)
                }
            )
            LazyColumn(){
                item {

                    ShelterDogDetails(
                        imagePickerOnClick = {
                            singlePhotoPicker.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        },
                        localUri = uiState.localUri,
                        dogName = uiState.dogName,
                        onDogNameChange = { viewModel.onDogNameChange(it) }
                    )
                }
            }
        }
    }
}