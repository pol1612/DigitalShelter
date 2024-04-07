package com.pol.sane.jove.digitalshelter.ui.common.composables.simples

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pol.sane.jove.digitalshelter.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(
    currentMenuValue: String,
    isMenuExpanded: Boolean,
    onIsMenuExpandedChange: (Boolean) -> Unit,
    menuValueUpdate: (String) -> Unit,
    menuItems: HashMap<String,String>,
    menuName: String,
    modifier: Modifier = Modifier
){
    ExposedDropdownMenuBox(
        expanded = isMenuExpanded,
        onExpandedChange = {
            onIsMenuExpandedChange(it)
        },
        modifier = modifier
    ) {

        OutlinedTextField(
            label = {
                    Text(text = menuName)
            },
            value = currentMenuValue,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isMenuExpanded)
            },
            modifier = Modifier
                .menuAnchor(),
        )
        ExposedDropdownMenu(
            expanded = isMenuExpanded,
            onDismissRequest = {
                onIsMenuExpandedChange(false)
            },

            ) {
            menuItems.forEach {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = it.value,
                            textAlign = TextAlign.Center
                        ) },
                    onClick = {
                        menuValueUpdate(it.key)
                        onIsMenuExpandedChange(false)
                    }
                )
            }

        }
    }
}

@Composable
fun DatePicker(date: String, modifier: Modifier = Modifier, onCalendarIconClick: () -> Unit, calendarIconEnabled: Boolean = true) {
    OutlinedTextField(
        label = { Text(stringResource(id = R.string.dog_birth_date))},
        readOnly = true,
        singleLine = true,
        modifier = modifier.width(290.dp),
        value = date,
        onValueChange = {},
        placeholder = {},
        trailingIcon = {
            IconButton(
                onClick = { onCalendarIconClick() },
                enabled = calendarIconEnabled
            ) {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = stringResource(R.string.calendar)) }
        }

    )
}