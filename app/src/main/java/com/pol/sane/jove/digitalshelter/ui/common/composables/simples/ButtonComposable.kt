

package com.pol.sane.jove.digitalshelter.ui.common.composables.simples

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.pol.sane.jove.digitalshelter.R

@Composable
fun BasicTextButton(@StringRes text: Int, modifier: Modifier, action: () -> Unit) {
  TextButton(onClick = action, modifier = modifier) { Text(text = stringResource(text)) }
}

@Composable
fun BasicButton(
  @StringRes text: Int,
  modifier: Modifier,
  action: () -> Unit,
  enabled: Boolean = true,
  colors: ButtonColors = ButtonDefaults.buttonColors(
    containerColor = MaterialTheme.colorScheme.primary,
    contentColor = MaterialTheme.colorScheme.onPrimary
    )
  ) {
  Button(
    onClick = action,
    modifier = modifier,
    colors = colors,
    enabled = enabled
  ) {
    Text(text = stringResource(text), fontSize = 16.sp)
  }
}

@Preview
@Composable
fun ButtonsPreview(){
  Column {
    BasicButton(text = R.string.username, modifier = Modifier, action = { /*TODO*/ })
    BasicTextButton(text = R.string.dog_paw_icon_content_description, modifier = Modifier, action = {})
  }
}
@Composable
fun DialogConfirmButton(@StringRes text: Int, action: () -> Unit) {
  Button(
    onClick = action,
    colors =
      ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
      )
  ) {
    Text(text = stringResource(text))
  }
}

@Composable
fun DialogCancelButton(@StringRes text: Int, action: () -> Unit) {
  Button(
    onClick = action,
    colors =
      ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
      )
  ) {
    Text(text = stringResource(text))
  }
}
