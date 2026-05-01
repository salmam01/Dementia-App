package com.example.dementiaapp.features.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions

@Composable
fun CheckBox(
    itemCompleted: Boolean,
    onItemCompleted: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .size(DSDimensions.CheckBoxSize)
            .clip(CircleShape)
            .background(
                color = if (itemCompleted)
                    DSColours.PositiveActionPrimary
                else
                    DSColours.Surface
            )
            .border(
                width = DSDimensions.BorderRadius3,
                color = DSColours.PositiveActionOutline,
                shape = CircleShape
            )
            .clickable { onItemCompleted() },
        contentAlignment = Alignment.Center
    ) {
        if (itemCompleted) {
            Icon(
                imageVector = Icons.Rounded.Check,
                contentDescription = null,
                tint = DSColours.OnPrimary,
                modifier = Modifier.size(DSDimensions.Icon4)
            )
        }
    }
}