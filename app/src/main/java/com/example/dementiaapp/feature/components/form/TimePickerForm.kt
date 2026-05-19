package com.example.dementiaapp.feature.components.form

import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.localization.LocalizedStrings
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun TimePickerForm(
    time: LocalTime,
    onTimeSelected: (LocalTime) -> Unit,

    label: String ?= null,
    colour: Color,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val strings = LocalizedStrings.current

    PickerFieldForm(
        label = label ?: strings.time,
        value = time.format(DateTimeFormatter.ofPattern("HH:mm")),
        colour = colour,
        leadingIcon = Icons.Rounded.Schedule,
        onClick = {
            TimePickerDialog(
                context,
                { _, hour, minute ->
                    onTimeSelected(LocalTime.of(hour, minute))
                },
                time.hour,
                time.minute,
                true
            ).show()
        },
        modifier = modifier
            .background(DSColours.Surface)
    )
}