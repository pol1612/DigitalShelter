package com.pol.sane.jove.digitalshelter.ui.common.composables.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pol.sane.jove.digitalshelter.R
import com.pol.sane.jove.digitalshelter.ui.common.composables.simples.BasicButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserSettingsScreen(
    userName: String,
    passwordRecoveryEmailSender: () -> Unit,
    logOut: () -> Unit,
    deleteAccount: () -> Unit,
    snackbarHostState: SnackbarHostState
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.user_settings)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer),

                )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier
                .padding(horizontal = 0.dp, vertical = 10.dp))
            Text(
                text = stringResource(R.string.hi, userName),
                modifier = Modifier.padding(
                    horizontal = 0.dp,
                    vertical = 10.dp
                ),
                fontSize = 20.sp
            )
            BasicButton(
                text = R.string.send_password_revovery_email,
                modifier = Modifier
                    .padding(10.dp),
                action = { passwordRecoveryEmailSender() }
            )
            BasicButton(
                text = R.string.log_out,
                modifier = Modifier.padding(10.dp),
                action = { logOut() }
            )
            BasicButton(
                text = R.string.delete_account_permanently,
                modifier = Modifier.padding(10.dp),
                action = { deleteAccount() }
            )
        }

    }
}

@Preview
@Composable
fun UserSettingsScreenPreview(){
    UserSettingsScreen(
        userName = "Pol shelter",
        passwordRecoveryEmailSender = {},
        logOut = {},
        deleteAccount = {},
        snackbarHostState = SnackbarHostState()
    )
}