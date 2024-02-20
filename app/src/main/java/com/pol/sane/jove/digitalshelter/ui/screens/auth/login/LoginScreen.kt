package com.pol.sane.jove.digitalshelter.ui.screens.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pol.sane.jove.digitalshelter.ui.common.EmailField
import com.pol.sane.jove.digitalshelter.ui.common.PasswordField
import com.pol.sane.jove.digitalshelter.R.string as AppText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
){
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(AppText.login_into_the_app)) },
                  colors = TopAppBarDefaults.mediumTopAppBarColors(
                      containerColor = MaterialTheme.colorScheme.primaryContainer
                  ))
        },
        content =  {
            Column(modifier = Modifier
                .padding(it),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                )
                EmailField(value = viewModel.value, onNewValue = )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                )
                PasswordField(value = , onNewValue = )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                )

            }
        }
    )
}

@Preview
@Composable
fun LoginScreenPreview(){
    LoginScreen()
}