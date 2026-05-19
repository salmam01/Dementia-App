package com.example.dementiaapp.feature.logs.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.design.DSTypography
import com.example.dementiaapp.localization.LocalizedStrings

@Composable
fun SummarySection(
    extended: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val strings = LocalizedStrings.current
    val arrowDirection =
        if (extended) -90f
        else 90f

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = DSDimensions.Space2,
                horizontal = DSDimensions.Space4
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = strings.summary,
                fontWeight = FontWeight.SemiBold,
                fontSize = DSTypography.Body.Medium,
                modifier = Modifier.weight(1f)
            )

            IconButton(
                onClick = onClick
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBackIosNew,
                    contentDescription = strings.summaryExtended,
                    tint = DSColours.OnSurface,
                    modifier = Modifier
                        .size(DSDimensions.Icon3)
                        .rotate(arrowDirection)
                )
            }
        }

        if (extended) {

        }

    }
    HorizontalDivider(
        thickness = DSDimensions.DividerThickness1,
        color = DSColours.Divider
    )
}

@Composable
fun SummaryItem(
    modifier: Modifier = Modifier
) {

}