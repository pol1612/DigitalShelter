package com.pol.sane.jove.digitalshelter.ui.screens.auth.signup

import android.widget.ToggleButton
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
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
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.pol.sane.jove.digitalshelter.R
import com.pol.sane.jove.digitalshelter.ui.common.BasicButton
import com.pol.sane.jove.digitalshelter.ui.common.EmailField
import com.pol.sane.jove.digitalshelter.ui.common.PasswordField
import com.pol.sane.jove.digitalshelter.ui.common.RepeatPasswordField
import com.pol.sane.jove.digitalshelter.ui.common.UsernameField
import com.pol.sane.jove.digitalshelter.ui.theme.Shapes
import com.pol.sane.jove.digitalshelter.R.string as AppText
import com.pol.sane.jove.digitalshelter.R.drawable as AppIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(AppText.register_into_the_app)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer),
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = Icons.Rounded.ArrowBack.name
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .fillMaxHeight(),

            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            var scrollState: ScrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {


            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
            )
            UsernameField(
                value = "",
                onNewValue = {})
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
            )
            EmailField(value = "", onNewValue = {})
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
            )

            PasswordField(
                value = "",
                onNewValue = {},
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
            )
            RepeatPasswordField(
                value = "",
                onNewValue = {}
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
                Switch(false,{})
                }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
            )
            if (true){
                val hydePark = LatLng(51.508610, -0.163611)
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(hydePark, 10f)
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.Start,) {
                    Text(text = "Choose your shelter's location.")
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                )
                GoogleMap(
                    modifier = Modifier
                        .width(300.dp)
                        .height(400.dp)
                        .clip(Shapes.medium),
                    cameraPositionState = cameraPositionState) {
                    Marker(
                        state = MarkerState(position = hydePark),
                        title = "Hyde Park",
                        snippet = "Marker in Hyde Park"
                    )
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                )
            }
            BasicButton(
                text = AppText.signup,
                modifier = Modifier
                    .width(280.dp)
            ) {

            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
            )
        }
        }
    }
}

@Preview
@Composable
fun SignUpScreenPreview(){
    SignUpScreen(navHostController = NavHostController(LocalContext.current))
}