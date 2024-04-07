
package com.pol.sane.jove.digitalshelter.ui.common.composables.simples

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.pol.sane.jove.digitalshelter.R
import com.pol.sane.jove.digitalshelter.R.drawable as AppIcon
import com.pol.sane.jove.digitalshelter.R.string as AppText


@Composable
fun BasicField(
  placeholder: Int = 0,
  value: String,
  onNewValue: (String) -> Unit,
  modifier: Modifier = Modifier,
  label: String = ""
) {
  OutlinedTextField(
    label = {
      if (!label.equals("")){
        Text(label)
      }

    },
    singleLine = true,
    modifier = modifier.width(290.dp),
    value = value,
    onValueChange = { onNewValue(it) },
    placeholder = {
      if(placeholder != 0){
        Text(stringResource(placeholder))
      }
    }
  )
}

@Composable
fun BasicBigField(
  placeholder: Int = 0,
  value: String,
  onNewValue: (String) -> Unit,
  modifier: Modifier = Modifier,
  label: String = ""

) {
  OutlinedTextField(
    label = {
      if (!label.equals("")){
        Text(label)
      }
    },
    singleLine = false,
    modifier = modifier
      .width(290.dp)
      .height(150.dp),
    value = value,
    onValueChange = { onNewValue(it) },
    placeholder = {
      if(placeholder != 0){
        Text(stringResource(placeholder))
      }
    }
  )
}



@Composable
fun EmailField(value: String, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
  OutlinedTextField(
    singleLine = true,
    modifier = modifier.width(290.dp),
    value = value,
    onValueChange = { onNewValue(it) },
    placeholder = { Text(stringResource(AppText.email)) },
    leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = stringResource(id = AppText.email)) }
  )
}

@Composable
fun UsernameField(value: String, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
  OutlinedTextField(
    singleLine = true,
    modifier = modifier.width(290.dp),
    value = value,
    onValueChange = { onNewValue(it) },
    placeholder = { Text(stringResource(AppText.username)) },
    leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = stringResource(id = AppText.username)) }
  )
}


@Composable
fun PasswordField(value: String, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
  PasswordField(value, AppText.password, onNewValue, modifier.width(290.dp))
}

@Composable
fun RepeatPasswordField(
  value: String,
  onNewValue: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  PasswordField(value, AppText.repeat_password, onNewValue, modifier.width(290.dp))
}

@Composable
private fun PasswordField(
  value: String,
  @StringRes placeholder: Int,
  onNewValue: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  var isVisible by remember { mutableStateOf(false) }

  val icon =
    if (isVisible) painterResource(AppIcon.ic_visibility_on)
    else painterResource(AppIcon.ic_visibility_off)

  val visualTransformation =
    if (isVisible) VisualTransformation.None else PasswordVisualTransformation()

  OutlinedTextField(
    modifier = modifier,
    value = value,
    onValueChange = { onNewValue(it) },
    placeholder = { Text(text = stringResource(placeholder)) },
    leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = stringResource(R.string.lock)) },
    trailingIcon = {
      IconButton(onClick = { isVisible = !isVisible }) {
        Icon(painter = icon, contentDescription = stringResource(R.string.visibility))
      }
    },
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    visualTransformation = visualTransformation
  )
}
