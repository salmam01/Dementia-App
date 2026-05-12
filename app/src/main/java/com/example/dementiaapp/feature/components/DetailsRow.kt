package com.example.dementiaapp.feature.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.design.DSTypography

@Composable
fun DetailsRow(
    label: String,
    detail: String?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(
                horizontal = DSDimensions.Space4,
                vertical = DSDimensions.Space5
            )
    ) {
        Text(
            text = label,
            fontSize = DSTypography.Body.Large,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.width(DSDimensions.Space2))

        Text(
            text = detail ?: "",
            fontSize = DSTypography.Body.Large,
            fontWeight = FontWeight.Normal
        )
    }
}