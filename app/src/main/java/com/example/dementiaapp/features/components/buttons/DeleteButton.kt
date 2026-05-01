package com.example.dementiaapp.features.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
fun DeleteButton(
    text: String ?= null,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onDeleteClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = DSColours.NegativeActionPrimary,
            contentColor = DSColours.OnPrimary
        ),
        contentPadding = PaddingValues(
            horizontal = DSDimensions.Space2
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
                imageVector = Icons.Rounded.Delete,
                contentDescription = null,
                modifier = Modifier
                    .size(DSDimensions.Icon1)
            )
            Spacer(modifier = Modifier.width(DSDimensions.Space1))
            Text(
                text = text ?: "Delete",
                fontSize = DSTypography.Body.Medium,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}