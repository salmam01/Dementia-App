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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions

@Composable
fun CheckBox(
    itemChecked: Boolean,
    onItemChecked: () -> Unit,
    modifier: Modifier = Modifier,

    shape: Shape = CircleShape,
    borderRadius: Dp = DSDimensions.BorderRadius3,
    borderColour: Color = DSColours.PositiveActionOutline,
    borderColourChecked: Color = borderColour,

    size: Dp = DSDimensions.CheckBoxSize,
    backgroundColour: Color = DSColours.Surface,
    backgroundColourChecked: Color = DSColours.PositiveActionPrimary,

    icon: ImageVector = Icons.Rounded.Check,
    contentDescription: String = "Item checked",
    tint: Color = DSColours.OnPrimary,
    iconSize: Dp = DSDimensions.Icon4
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(shape)
            .background(
                color = if (itemChecked)
                    backgroundColourChecked
                else
                    backgroundColour
            )
            .border(
                width = borderRadius,
                color =
                    if (itemChecked)
                        borderColourChecked
                    else
                        borderColour,
                shape = shape
            )
            .clickable { onItemChecked() },
        contentAlignment = Alignment.Center
    ) {
        if (itemChecked) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = tint,
                modifier = Modifier.size(iconSize)
            )
        }
    }
}