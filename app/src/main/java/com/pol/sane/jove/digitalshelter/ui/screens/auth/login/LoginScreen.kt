package com.pol.sane.jove.digitalshelter.ui.screens.auth.login

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
import com.pol.sane.jove.digitalshelter.ui.common.composables.simples.BasicTextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.pol.sane.jove.digitalshelter.ui.common.composables.simples.BasicButton
import com.pol.sane.jove.digitalshelter.ui.common.composables.simples.EmailField
import com.pol.sane.jove.digitalshelter.ui.common.composables.simples.PasswordField
import android.util.Log
import com.pol.sane.jove.digitalshelter.R
import com.pol.sane.jove.digitalshelter.R.string as AppText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
    navHostController: NavHostController
){
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }


    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(stringResource(AppText.login_into_the_app)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                      containerColor = MaterialTheme.colorScheme.primaryContainer),

            )
        },
        content =  {
            var scrollState: ScrollState = rememberScrollState()
            Column(modifier = Modifier
                .padding(it)
                .verticalScroll(scrollState),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                )
                EmailField(value = uiState.email, onNewValue = {
                    newValue -> viewModel.onEmailChange(newValue)
                })
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                )
                PasswordField(value = uiState.password, onNewValue = {
                    newValue -> viewModel.onPasswordChange(newValue)
                })
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                )
                Column(
                    modifier = Modifier
                        .width(340.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    BasicButton(
                        text = AppText.sign_in,
                        modifier = Modifier
                            .width(320.dp)
                            .padding(horizontal = 0.dp, vertical = 8.dp),
                        action = {
                            viewModel.onLoginClick(navHostController)
                        },
                        enabled = uiState.isSignInButtonEnabled

                    )
                    var emailSentText = stringResource(id = R.string.the_email_was_successfully_sent)
                    BasicTextButton(AppText.forgot_password, Modifier
                        .fillMaxWidth()
                    ){
                        viewModel.sendRecoveryEmail(emailSentText)


                    }
                    BasicTextButton(
                        text = AppText.not_registered_click_here_to_sign_up,
                        Modifier
                            .fillMaxWidth()
                            //.padding(16.dp, 8.dp, 16.dp, 0.dp),
                    ) {
                        viewModel.onSignUpClick(navHostController)
                    }
                }

                LaunchedEffect(key1 = uiState.snackBarText){
                    if(uiState.snackBarText != ""){
                        val snackbarResult =  snackbarHostState.showSnackbar(
                            message = uiState.snackBarText,
                            duration = SnackbarDuration.Short
                        )
                        if (snackbarResult == SnackbarResult.Dismissed){
                            Log.i("LoginScreen::Snackbar","dismissed")
                            viewModel.deleteSnackBarText()
                        }
                    }
                }

            }

        }
    )

}



@Preview
@Composable
fun LoginScreenPreview(){
    LoginScreen(viewModel(), NavHostController(LocalContext.current))
}