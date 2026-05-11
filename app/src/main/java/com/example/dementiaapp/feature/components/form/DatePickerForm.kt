package com.example.dementiaapp.feature.components.form

import android.app.DatePickerDialog
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DatePickerForm(
    date: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    colour: Color,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    PickerFieldForm(
        label = "Date",
        value = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
        colour = colour,
        trailingIcon = Icons.Rounded.DateRange,
        onClick = {
            DatePickerDialog(
                context,
                {_, year, month, day ->
                    onDateSelected(LocalDate.of(year, month + 1, day))
                },
                date.year,
                date.monthValue - 1,
                date.dayOfMonth,
            ).show()
        },
        modifier = modifier
    )
}