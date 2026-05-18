package com.example.dementiaapp.feature.components.form

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.design.DSTypography

@Composable
fun TextFieldForm(
    label: String,
    initialValue: String,
    onValueChange: (String) -> Unit,
    colour: Color,
    backgroundColour: Color = DSColours.Surface,
    modifier: Modifier = Modifier
) {
    var isFocused by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColour)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(DSDimensions.Space4)
        ) {
            Text(
                text = label,
                fontSize = DSTypography.Body.Large,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(DSDimensions.Space2))

            OutlinedTextField(
                value = initialValue,
                onValueChange = onValueChange,
                shape = RoundedCornerShape(DSDimensions.CornerRadius1),
                textStyle = TextStyle(
                    fontSize = DSTypography.Body.Small
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = DSColours.Surface,
                    unfocusedContainerColor = DSColours.Surface,
                    focusedBorderColor = colour,
                    unfocusedBorderColor = DSColours.Divider,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { isFocused = it.isFocused }
                    .border(
                        width = DSDimensions.BorderRadius2,
                        color = if (isFocused) colour else DSColours.Divider,
                        shape = RoundedCornerShape(DSDimensions.CornerRadius1)
                    )
            )
        }
        HorizontalDivider(
            thickness = DSDimensions.DividerThickness1,
            color = DSColours.Divider
        )
    }
}