package com.example.dementiaapp.features.components.buttons

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.design.DSTypography

@Composable
fun CancelButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = DSColours.Surface,
            contentColor = DSColours.EditPrimary
        ),
        contentPadding = PaddingValues(DSDimensions.Space4),
        shape = RoundedCornerShape(DSDimensions.CornerRadius1),
        modifier = modifier
            .border(
                width = DSDimensions.BorderRadius2,
                color = DSColours.EditPrimary,
                shape = RoundedCornerShape(DSDimensions.CornerRadius1)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = "Cancel",
                modifier = Modifier
                    .size(DSDimensions.Icon5)
            )
            Spacer(modifier = Modifier.width(DSDimensions.Space2))
            Text(
                text = "Cancel",
                fontSize = DSTypography.Headline.Large,
                fontWeight = FontWeight.Bold
            )
        }
    }
}