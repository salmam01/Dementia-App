package com.example.dementiaapp.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions

@Composable
fun BottomNavigationBar(
    selectedKey: NavKey,
    onSelectKey: (NavKey) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DSColours.Primary),
    ) {
        TOP_LEVEL_DESTINATIONS.forEach { (destination, data) ->
            CustomNavigationBarItem(
                selected = destination == selectedKey,
                onClick = { onSelectKey(destination) },
                icon = data.icon,
                contentDescription = data.title
            )
        }
    }
}

@Composable
fun CustomNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(
                color =
                    if (selected) DSColours.Secondary
                    else Color.Transparent
            )
            .clickable(onClick = onClick)
            .padding(
                horizontal = DSDimensions.Space5,
                vertical = DSDimensions.Space3
            )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint =
                if (selected) DSColours.OnSecondary
                else DSColours.OnPrimary,
            modifier = Modifier.size(DSDimensions.Icon7)
        )
    }
}