package com.example.dementiaapp.feature.components.form

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.ArrowDropUp
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.design.DSTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropDownForm(
    options: List<T>,
    selectedOption: T,
    onOptionSelected: (T) -> Unit,
    optionText: (T) -> String,
    optionIcon: ((T) -> ImageVector)? = null,

    label: String,
    colour: Color,
    leadingIcon: ImageVector,

    modifier: Modifier = Modifier
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val trailingIcon =
        if (expanded) Icons.Rounded.ArrowDropUp
        else Icons.Rounded.ArrowDropDown

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = Modifier
            .padding(horizontal = DSDimensions.Space2)
            .background(DSColours.Surface)
    ) {
        PickerFieldForm(
            label = label,
            value = optionText(selectedOption),
            onClick = { expanded = true },
            colour = colour,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(DSColours.Surface)
                        ) {
                            if (optionIcon != null) {
                                Icon(
                                    imageVector = optionIcon.invoke(option),
                                    contentDescription = null,
                                    tint = colour,
                                    modifier = Modifier
                                        .size(DSDimensions.Icon3)
                                        .padding(start = DSDimensions.Space1)
                                )
                                Spacer(modifier = Modifier.width(DSDimensions.Space4))
                            }
                            Text(
                                text = optionText(option),
                                fontWeight = FontWeight.Normal,
                                fontSize = DSTypography.Body.Small
                            )
                        }
                    },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}