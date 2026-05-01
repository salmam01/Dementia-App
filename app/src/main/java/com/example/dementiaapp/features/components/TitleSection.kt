package com.example.dementiaapp.features.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.design.DSTypography

@Composable
fun TitleSection(
    title: String,
    icon: ImageVector,
    colour: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(DSColours.SurfaceVariant)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(DSDimensions.Space4)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = colour,
                modifier = Modifier
                    .size(DSDimensions.Icon6)
            )

            Spacer(modifier = Modifier.width(DSDimensions.Space3))

            Text(
                text = title,
                fontSize = DSTypography.Headline.Large,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
            )
        }

        HorizontalDivider(
            thickness = DSDimensions.DividerThickness1,
            color = DSColours.Divider
        )
    }
}