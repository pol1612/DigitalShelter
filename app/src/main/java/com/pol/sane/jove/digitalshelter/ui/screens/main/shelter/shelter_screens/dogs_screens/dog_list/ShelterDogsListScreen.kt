package com.pol.sane.jove.digitalshelter.ui.screens.main.shelter.shelter_screens.dogs_screens.dog_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
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
import com.pol.sane.jove.digitalshelter.data.pojo.Dog
import com.pol.sane.jove.digitalshelter.ui.screens.main.shelter.shelter_screens.dogs_screens.ShelterDogsScreensUiState
import com.pol.sane.jove.digitalshelter.ui.screens.main.shelter.shelter_screens.dogs_screens.ShelterDogsScreensViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShelterDogsListScreen(navHostController: NavHostController) {
    var sharedViewModel: ShelterDogsScreensViewModel = viewModel()
    var uiState: ShelterDogsScreensUiState = sharedViewModel.uiState.collectAsState().value
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.shelter_dog_list_title)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        },
        snackbarHost = {},
        floatingActionButton = {
            FloatingActionButton(
                onClick = { sharedViewModel.createDogButtonOnClick(navHostController) },
                shape = CircleShape
            ){
                Icon(Icons.Default.Add, contentDescription = stringResource(R.string.dog_creation_floating_button))
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            if(uiState.dogsList.size == 0){
                EmptyDogsList()
            }else{
                DogsList(uiState.dogsList)
            }
        }
        LaunchedEffect(key1 = uiState.dogsList){

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

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun DogsList(dogsHashMap: HashMap<String, Dog>) {
    Column {
        dogsHashMap.forEach(){
            key(it.key) { //Dog ID
                DogItem(dog = it.value)
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogItem(dog: Dog){
    Card(
        onClick = {

        },
        modifier = Modifier
    ) {
        Row {
            Text(text = dog.dogName)
            var birthDate: LocalDate = dog.dogDateOfBirth.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            var dogAge: Int = Period.between(birthDate, LocalDateTime.now().toLocalDate()).years
            Text(text = dogAge.toString())
        }
    }
}
