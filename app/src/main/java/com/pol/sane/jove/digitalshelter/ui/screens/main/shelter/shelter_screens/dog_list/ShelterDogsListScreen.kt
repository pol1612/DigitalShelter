package com.pol.sane.jove.digitalshelter.ui.screens.main.shelter.shelter_screens.dog_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.pol.sane.jove.digitalshelter.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShelterDogsListScreen(navHostController: NavHostController) {
    var viewModel: ShelterDogsListScreenViewModel = viewModel()
    var uiState: ShelterDogsListScreenUiState = viewModel.uiState.collectAsState().value
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.shelter_dog_list_title)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        },
        snackbarHost = {}
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            if(uiState.dogsList.size == 0){
                EmptyDogsList()
            }else{
                DogsList()
            }
        }
    }
}

@Composable
fun EmptyDogsList(){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.pawprint),
            contentDescription = stringResource(id = R.string.dog_paw_image),
            modifier = Modifier
                .padding(top = 60.dp, start = 0.dp, end = 0.dp, bottom = 10.dp)
        )
        Text(
            text = stringResource(R.string.empty_shelter_dogs_list_text),
            fontSize = 20.sp,
            modifier = Modifier
                .width(300.dp),
            textAlign = TextAlign.Center

        )
    }
}

@Composable
fun DogsList(){

}
