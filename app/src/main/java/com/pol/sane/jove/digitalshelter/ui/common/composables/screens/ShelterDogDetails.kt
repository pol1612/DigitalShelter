package com.pol.sane.jove.digitalshelter.ui.common.composables.screens

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pol.sane.jove.digitalshelter.R
import com.pol.sane.jove.digitalshelter.data.pojo.Dog
import com.pol.sane.jove.digitalshelter.ui.common.composables.simples.BasicField

@Composable
fun ShelterDogDetails(
    disabled: Boolean = false,
    dog: Dog? = null,
    imagePickerOnClick: () -> Unit,
    localUri: Uri?,
    dogName: String,
    onDogNameChange: (String) -> Unit
){

    Column(
    ) {

        if(disabled){
            //reading a dog

        }else{
            if (dog == null){
                //creating a dog
                DogDetailsCreation(
                    imagePickerOnClick,
                    localUri,
                    dogName,
                    onDogNameChange
                )
            }else{
                //updating a dog

            }

        }

    }
}

@Preview
@Composable
fun ShelterDogDetailsPreview(){
    ShelterDogDetails(
        imagePickerOnClick = {},
        localUri = null,
        dogName = "",
        onDogNameChange = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogDetailsCreation(
    imagePickerOnClick: () -> Unit,
    localUri: Uri?,
    dogName: String,
    onDogNameChange: (String) -> Unit

){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier
            .height(45.dp)
        )
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            onClick = {imagePickerOnClick()}
        ) {
            if(localUri == null){
                Icon(
                    Icons.Outlined.Add,
                    contentDescription = stringResource(R.string.button_to_select_an_image_for_the_dog),
                    modifier = Modifier
                        .padding(horizontal = 140.dp, vertical = 90.dp)
                )
            }
            else{
                AsyncImage(
                    model = localUri,
                    contentDescription = stringResource(R.string.the_image_that_has_been_picked_from_the_gallery_to_represent_the_new_dog),
                    modifier = Modifier
                        .size(width = 304.dp, height = 203.dp),
                    contentScale = ContentScale.FillBounds
                )
            }

        }
        Spacer(modifier = Modifier
            .height(45.dp)
        )
        BasicField(
            text = R.string.dog_name,
            value = dogName,
            onNewValue = { onDogNameChange(it) }
        )
    }
}