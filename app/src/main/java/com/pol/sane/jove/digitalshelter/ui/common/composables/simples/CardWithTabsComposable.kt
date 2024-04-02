package com.pol.sane.jove.digitalshelter.ui.common.composables.simples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pol.sane.jove.digitalshelter.ui.theme.DigitalShelterAppTheme

@Composable
fun CardWithTabs(
    content: @Composable () -> Unit = { Surface(
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 8.dp,
        modifier = Modifier.fillMaxWidth(),


        ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(16.dp),
        ) {
            Text("Content for Merged Tab")

            Spacer(modifier = Modifier.height(16.dp))

            // Separate Tab
            Box(
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    //.clickable { separateTabIndex = (separateTabIndex + 1) % 2 }
                    .padding(8.dp)
            ) {
                Text(
                    text = "",//tabs[separateTabIndex],
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }
        }
    } },
) {
    var mergedTabIndex by remember { mutableStateOf(0) }
    var separateTabIndex by remember { mutableStateOf(1) }

    val tabs = listOf("Merged Tab", "Separate Tab")

    Column(modifier = Modifier.padding(16.dp)) {
        TabRow(
            selectedTabIndex = mergedTabIndex,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            //backgroundColor = Color.LightGray,
            //contentColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = index == mergedTabIndex,
                    onClick = { mergedTabIndex = index },
                    modifier = Modifier.clip(MaterialTheme.shapes.medium)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        content()

    }
}

@Preview
@Composable
fun CardWithTabsPreview(){
    DigitalShelterAppTheme {
        CardWithTabs()
    }

}