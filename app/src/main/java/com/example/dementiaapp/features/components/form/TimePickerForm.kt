package com.example.dementiaapp.features.components.form

import android.app.TimePickerDialog
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun TimePickerForm(
    time: LocalTime,
    onTimeSelected: (LocalTime) -> Unit,
    colour: Color,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    PickerFieldForm(
        label = "Time",
        value = time.format(DateTimeFormatter.ofPattern("HH:mm")),
        colour = colour,
        trailingIcon = Icons.Rounded.Schedule,
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
    )
}