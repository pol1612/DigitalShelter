package com.pol.sane.jove.digitalshelter.ui.common.composables.screens

import android.net.Uri
import android.util.Log
import android.widget.TextSwitcher
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import   com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.vanpra.composematerialdialogs.MaterialDialog
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pol.sane.jove.digitalshelter.R
import com.pol.sane.jove.digitalshelter.data.pojo.Dog
import com.pol.sane.jove.digitalshelter.ui.common.composables.simples.BasicBigField
import com.pol.sane.jove.digitalshelter.ui.common.composables.simples.BasicField
import com.pol.sane.jove.digitalshelter.ui.common.composables.simples.DatePicker
import com.pol.sane.jove.digitalshelter.ui.common.composables.simples.DropDownMenu
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun ShelterDogDetails(
    disabled: Boolean = false,
    dog: Dog? = null,
    onImagePickerClick: () -> Unit,
    localUri: Uri?,
    dogName: String,
    onDogNameChange: (String) -> Unit,
    dogDescription: String,
    onDogDescriptionChange: (String) -> Unit,
    updateDogBirthDate: (LocalDate) -> Unit,
    dogBirthDate: LocalDate,
    dogStatusMenuIsExpanded: Boolean,
    onDogStatusIsExpandedChange: (Boolean) -> Unit,
    dogStatus: String,
    dogStatusUpdate: (String) -> Unit,

    dogSizeMenuIsExpanded: Boolean,
    onDogSizeMenuIsExpandedChange: (Boolean) -> Unit,
    dogSize: String,
    dogSizeUpdate: (String) -> Unit,
    isDogMale: Boolean,

    onMaleRadioButtonClick: () -> Unit,
    onFemaleRadioButtonClick: () -> Unit,

    ){

    Column(
    ) {

        if(disabled){
            //reading a dog

        }else{
            if (dog == null){
                //creating a dog
                DogDetailsCreation(
                    onImagePickerClick = onImagePickerClick,
                    localUri = localUri,
                    dogName = dogName,
                    onDogNameChange = onDogNameChange,
                    dogDescription = dogDescription,
                    onDogDescriptionChange = onDogDescriptionChange,
                    updateDogBirthDate = updateDogBirthDate,
                    dogBirthDate = dogBirthDate,
                    dogStatusMenuIsExpanded = dogStatusMenuIsExpanded,
                    onDogStatusMenuIsExpandedChange = onDogStatusIsExpandedChange,
                    dogStatus = dogStatus,
                    dogStatusUpdate = dogStatusUpdate,
                    dogSizeMenuIsExpanded = dogSizeMenuIsExpanded,
                    onDogSizeMenuIsExpandedChange= onDogSizeMenuIsExpandedChange,
                    dogSize = dogSize,
                    dogSizeUpdate = dogSizeUpdate,
                    isDogMale = isDogMale,
                    onMaleRadioButtonClick = onMaleRadioButtonClick,
                    onFemaleRadioButtonClick = onFemaleRadioButtonClick
                )
            }else{
                //updating a dog

            }

        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogDetailsCreation(
    onImagePickerClick: () -> Unit,
    localUri: Uri?,
    dogName: String,
    onDogNameChange: (String) -> Unit,
    dogDescription: String,
    onDogDescriptionChange: (String) -> Unit,
    updateDogBirthDate: (LocalDate) -> Unit,
    dogBirthDate: LocalDate,

    dogStatusMenuIsExpanded: Boolean,
    onDogStatusMenuIsExpandedChange: (Boolean) -> Unit,
    dogStatus: String,
    dogStatusUpdate: (String) -> Unit,

    dogSizeMenuIsExpanded: Boolean,
    onDogSizeMenuIsExpandedChange: (Boolean) -> Unit,
    dogSize: String,
    dogSizeUpdate: (String) -> Unit,
    isDogMale: Boolean ,
    onMaleRadioButtonClick: () -> Unit,
    onFemaleRadioButtonClick: () -> Unit,


    ){
    val dateDialogState = rememberMaterialDialogState()
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
                onClick = {onImagePickerClick()},
                modifier = Modifier.padding(vertical = 45.dp, horizontal = 0.dp)
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
        }
        item {

            BasicField(
                label = stringResource(id =  R.string.dog_name),
                value = dogName,
                onNewValue = { onDogNameChange(it) }
            )
        }
        item {

            BasicBigField(
                label = stringResource(id =  R.string.dog_description),
                value = dogDescription,
                onNewValue = {onDogDescriptionChange(it)},
                modifier = Modifier
                    .padding(vertical = 45.dp, horizontal = 0.dp)
            )
        }
        item {
            var date = dogBirthDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
            DatePicker(
                date = date,
                onCalendarIconClick = {
                    dateDialogState.show()
                }
            )
        }
        item {
            var dogStatuses = hashMapOf<String,String>(
                "Available" to stringResource(R.string.available),
                "Reserved" to stringResource(R.string.reserved),
                "Adopted" to stringResource(R.string.adopted)
            )
            var dogSizes = hashMapOf<String,String>(
                "Small" to stringResource(R.string.small),
                "Medium" to stringResource(R.string.medium),
                "Big" to stringResource(R.string.big)
            )

            Row(
                modifier = Modifier
                    .padding(
                        vertical = 45.dp,
                        horizontal = 0.dp
                    )

            ) {

                DropDownMenu(
                    currentMenuValue = dogSizes[dogSize].toString(),
                    isMenuExpanded = dogSizeMenuIsExpanded,
                    onIsMenuExpandedChange = onDogSizeMenuIsExpandedChange,
                    menuValueUpdate = dogSizeUpdate,
                    menuItems = dogSizes,
                    menuName = stringResource(R.string.dog_size),
                    modifier = Modifier
                        .width(140.dp)
                )
                Spacer(modifier = Modifier
                    .width(10.dp)
                )
                DropDownMenu(
                    currentMenuValue = dogStatus,
                    isMenuExpanded = dogStatusMenuIsExpanded,
                    onIsMenuExpandedChange = onDogStatusMenuIsExpandedChange,
                    menuValueUpdate = dogStatusUpdate,
                    menuItems = dogStatuses,
                    menuName = stringResource(R.string.dog_status),
                    modifier = Modifier
                        .width(140.dp)
                )

            }
        }
        item {
            Column(
                modifier = Modifier
                    .padding(
                        bottom = 45.dp
                    ),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    RadioButton(
                        selected = isDogMale,
                        onClick = { onMaleRadioButtonClick() }
                    )
                    Text(
                        text = stringResource(id = R.string.male)
                    )
                    Spacer(modifier = Modifier.width(190.dp))
                }
                Row(verticalAlignment = Alignment.CenterVertically) {

                    RadioButton(
                        selected = !isDogMale,
                        onClick = { onFemaleRadioButtonClick() }
                    )
                    Text(text = stringResource(R.string.female_dog))
                }
            }
        }
    }
    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(
                text = stringResource(R.string.ok),
                textStyle = TextStyle(color = MaterialTheme.colorScheme.tertiary)
            )
            negativeButton(
                text = stringResource(R.string.cancel),
                textStyle = TextStyle(color = MaterialTheme.colorScheme.tertiary)
            )
        },
    ){
        datepicker(
            title = stringResource(R.string.choose_the_dog_s_birth_date),
            colors = DatePickerDefaults.colors(
                headerBackgroundColor = MaterialTheme.colorScheme.tertiary,
                dateActiveBackgroundColor = MaterialTheme.colorScheme.tertiary
            )
        ){
            Log.i("datepicker::onDateChange", "${it.toString()}")
            updateDogBirthDate(it)
        }
    }
}