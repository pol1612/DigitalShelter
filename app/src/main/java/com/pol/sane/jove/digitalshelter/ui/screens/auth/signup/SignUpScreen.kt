package com.pol.sane.jove.digitalshelter.ui.screens.auth.signup

import android.util.Log
import android.view.MotionEvent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

import com.google.maps.android.compose.GoogleMap
import com.pol.sane.jove.digitalshelter.ui.common.BasicButton
import com.pol.sane.jove.digitalshelter.ui.common.EmailField
import com.pol.sane.jove.digitalshelter.ui.common.PasswordField
import com.pol.sane.jove.digitalshelter.ui.common.RepeatPasswordField
import com.pol.sane.jove.digitalshelter.ui.common.UsernameField
import com.pol.sane.jove.digitalshelter.ui.theme.Shapes
import com.pol.sane.jove.digitalshelter.R.string as AppText

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class,
    ExperimentalPermissionsApi::class
)
@Composable
fun SignUpScreen(
    navHostController: NavHostController,
    viewModel: SignUpViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val locationPermissionsState = rememberMultiplePermissionsState(
        listOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION,

            )
    )
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(AppText.register_into_the_app)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer),
                navigationIcon = {
                    IconButton(onClick = {viewModel.goBack(navHostController)}) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = Icons.Rounded.ArrowBack.name
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        var scrollState: ScrollState = rememberScrollState()
        var columnScrollingEnabled: Boolean by remember { mutableStateOf(true) }
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .fillMaxHeight(),
                //.verticalScroll(scrollState,  columnScrollingEnabled),

            horizontalAlignment = Alignment.CenterHorizontally,
            userScrollEnabled = columnScrollingEnabled
        ) {
            item {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {


                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp)
                    )
                    UsernameField(
                        value = uiState.username,
                        onNewValue = {viewModel.onUsernameChange(it)})
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp)
                    )
                    EmailField(
                        value = uiState.email,
                        onNewValue = {viewModel.onEmailChange(it)})
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp)
                    )

                    PasswordField(
                        value = uiState.password,
                        onNewValue = {viewModel.onPasswordChange(it)},
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp)
                    )
                    RepeatPasswordField(
                        value = uiState.repeatedPassword,
                        onNewValue = {viewModel.onRepeatedPasswordChange(it)}
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Do you want to register as a Shelter?", modifier = Modifier
                            .padding(start = 20.dp)
                            .width(200.dp))
                        Spacer(modifier = Modifier
                            .width(25.dp))

                        Switch(
                            uiState.isShelter,
                            onCheckedChange = {
                                viewModel.onIsShelterChange(it, locationPermissionsState, context)
                            }
                        )
                    }

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                    )
                }
            }
            if(uiState.isShelter) {
                item {
                    LaunchedEffect(locationPermissionsState.revokedPermissions.size){
                        if(locationPermissionsState.revokedPermissions.size == 0){
                            Log.i("LaunchedEffect","getting location, rev perm size = ${locationPermissionsState.revokedPermissions.size}")
                            viewModel.makeCurrentLocationCameraLocation(context)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 60.dp),
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        Text(text = "Click to pick your shelter's location.")
                    }
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp)
                    )
                    val cameraPositionState = uiState.cameraLocation
                    GoogleMap(
                        modifier = Modifier
                            .width(300.dp)
                            .height(400.dp)
                            .clip(Shapes.medium)
                            .motionEventSpy {
                                when (it.action) {
                                    MotionEvent.ACTION_DOWN -> {
                                        columnScrollingEnabled = false
                                    }

                                    MotionEvent.ACTION_UP -> {
                                        columnScrollingEnabled = true
                                    }
                                }
                            },
                        cameraPositionState = cameraPositionState
                    )

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp)
                    )
                }
            }
            item {
                BasicButton(
                    text = AppText.signup,
                    modifier = Modifier
                        .width(280.dp),
                    action = {}
                )
                Spacer(
                    modifier = Modifier
                        //.fillMaxWidth()
                        .height(100.dp)
                )
            }

        }
    }
}


@Preview
@Composable
fun SignUpScreenPreview(){
    SignUpScreen(
        navHostController = NavHostController(LocalContext.current),
        viewModel = SignUpViewModel())
}