package com.example.dementiaapp.feature.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun CheckBoxRound(
    itemChecked: Boolean,
    onItemChecked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(DSDimensions.CheckBoxSize)
            .clip(RoundedCornerShape(DSDimensions.CornerRadius1))
            .background(
                color = if (itemChecked)
                    DSColours.CheckBoxBackground
                else
                    DSColours.Surface
            )
            .border(
                width = DSDimensions.BorderRadius3,
                color = DSColours.CheckBoxOutline,
                shape = RoundedCornerShape(DSDimensions.CornerRadius1)
            )
            .clickable { onItemChecked() },
        contentAlignment = Alignment.Center
    ) {
        if (itemChecked) {
            Icon(
                imageVector = Icons.Rounded.Check,
                contentDescription = null,
                tint = DSColours.OnPrimary,
                modifier = Modifier.size(DSDimensions.Icon4)
            )
        }
    }
}