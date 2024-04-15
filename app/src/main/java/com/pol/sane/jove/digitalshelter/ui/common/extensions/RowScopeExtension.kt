package com.pol.sane.jove.digitalshelter.ui.common.extensions

import android.service.autofill.OnClickAction
import android.util.Log
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@Composable
fun RowScope.AddBottBarNavItem(
    selected: Boolean,
    onClick: () -> Unit,
    iconDescription: Int,
    iconImage: Int,
){
    NavigationBarItem(
        selected = selected,
        onClick = { onClick() },
        icon = {
            Icon(
                painter = painterResource(id = iconImage),
                contentDescription = stringResource(id = iconDescription)
            )
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.onSurface,
            indicatorColor = MaterialTheme.colorScheme.onTertiary,
            unselectedIconColor = MaterialTheme.colorScheme.onSurface,
        ),
        enabled = true
    )
}