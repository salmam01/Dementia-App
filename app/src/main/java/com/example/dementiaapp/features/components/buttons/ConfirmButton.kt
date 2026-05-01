package com.example.dementiaapp.features.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Delete
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
fun ConfirmButton(
    text: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = DSColours.PositiveActionPrimary,
            contentColor = DSColours.OnPrimary
        ),
        contentPadding = PaddingValues(
            vertical = DSDimensions.Space2,
            horizontal = DSDimensions.Space4
        ),
        shape = RoundedCornerShape(DSDimensions.CornerRadius1),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            Icon(
                imageVector = Icons.Rounded.Check,
                contentDescription = "Confirm",
                modifier = Modifier
                    .size(DSDimensions.Icon4)
            )
            Spacer(modifier = Modifier.width(DSDimensions.Space1))
            Text(
                text = text ?: "Confirm",
                fontSize = DSTypography.Headline.Large,
                fontWeight = FontWeight.Bold
            )
        }
    }
}