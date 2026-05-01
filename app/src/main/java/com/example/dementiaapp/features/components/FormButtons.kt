package com.example.dementiaapp.features.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.features.components.buttons.AddOrSaveButton
import com.example.dementiaapp.features.components.buttons.CancelButton

@Composable
fun FormButtons(
    text: String ?= null,
    colour: Color,
    isEditing: Boolean,
    isEnabled: Boolean,
    onAddOrSaveClick: () -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(DSDimensions.Space4)
    ) {
        CancelButton(
            onClick = onCancelClick
        )

        AddOrSaveButton(
            text = text,
            colour = colour,
            isEditing = isEditing,
            isEnabled = isEnabled,
            onClick = onAddOrSaveClick
        )
    }
}