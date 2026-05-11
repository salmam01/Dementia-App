package com.example.dementiaapp.feature.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.design.DSTypography

@Composable
fun StickyActionButton(
    text: String,
    icon: ImageVector? = null,
    colour: Color,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shadowElevation = 12.dp,
        tonalElevation = 0.dp,
        shape = RectangleShape,
        modifier = modifier
    ) {
        Button(
            onClick = onAddClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = colour,
                contentColor = DSColours.OnPrimary
            ),
            shape = RectangleShape,
            modifier = modifier
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(
                        vertical = DSDimensions.Space3,
                        horizontal = DSDimensions.Space1
                    )
            ) {
                Icon(
                    imageVector = icon ?: Icons.Rounded.Add,
                    contentDescription = null,
                    modifier = Modifier
                        .size(DSDimensions.Icon6)
                )
                Spacer(modifier = Modifier.width(DSDimensions.Space2))
                Text(
                    text = text,
                    fontSize = DSTypography.Display.ExtraSmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}