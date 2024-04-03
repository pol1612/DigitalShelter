package com.pol.sane.jove.digitalshelter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.pol.sane.jove.digitalshelter.ui.DigitalShelter
import com.pol.sane.jove.digitalshelter.ui.theme.DigitalShelterAppTheme
import org.koin.core.component.KoinComponent


class MainActivity : ComponentActivity(), KoinComponent {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DigitalShelterAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DigitalShelter()
                }
            }
        }
    }
}


