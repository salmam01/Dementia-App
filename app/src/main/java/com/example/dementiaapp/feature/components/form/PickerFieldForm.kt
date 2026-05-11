package com.example.dementiaapp.feature.components.form

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.design.DSTypography

@Composable
fun PickerFieldForm(
    label: String,
    value: String,
    colour: Color,
    trailingIcon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onClick)
            ) {
                OutlinedTextField(
                    value = value,
                    onValueChange = {},
                    readOnly = true,
                    enabled = false,
                    textStyle = TextStyle(
                        fontSize = DSTypography.Body.Medium,
                        color = DSColours.OnSurface
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = trailingIcon,
                            contentDescription = null,
                            tint = colour,
                            modifier = Modifier
                                .size(DSDimensions.Icon3)
                                .padding(start = DSDimensions.Space1)
                        )
                    },
                    shape = RoundedCornerShape(DSDimensions.CornerRadius1),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = DSDimensions.BorderRadius2,
                            color = DSColours.Divider,
                            shape = RoundedCornerShape(DSDimensions.CornerRadius1)
                        )
                )
            }
        }

        HorizontalDivider(
            thickness = DSDimensions.DividerThickness1,
            color = DSColours.Divider
        )
    }
}