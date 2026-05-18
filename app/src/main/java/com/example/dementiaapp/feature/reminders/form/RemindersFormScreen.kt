package com.example.dementiaapp.feature.reminders.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.domain.models.Reminder
import com.example.dementiaapp.feature.components.buttons.FormButtons
import com.example.dementiaapp.feature.components.TitleSection
import com.example.dementiaapp.feature.components.form.DatePickerForm
import com.example.dementiaapp.feature.components.form.TextFieldForm
import com.example.dementiaapp.feature.components.form.TimePickerForm
import com.example.dementiaapp.feature.strings
import com.example.dementiaapp.localization.LocalizedStrings
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun RemindersFormScreen(
    selectedReminderId: String?,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: RemindersFormViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val strings = LocalizedStrings.current

    LaunchedEffect(selectedReminderId) {
        viewModel.clearState()
        if (selectedReminderId != null) {
            viewModel.getReminderById(id = selectedReminderId)
        }
    }

    val isEditing = state.isEditing
    val title =
        if (isEditing) "${strings.edit} ${strings.reminders}"
        else "${strings.add} ${strings.diary}"

    val icon =
        if (isEditing) Icons.Rounded.Edit
        else Icons.Rounded.Add

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DSColours.Surface)
    ) {
        TitleSection(
            title = title,
            icon = icon,
            colour = DSColours.FeatureColours.Reminders.Primary
        )

        RemindersFormContent(
            draftReminder = state.draftReminder,
            onMessageChange = { viewModel.setMessage(message = it) },
            onDateSelected = { viewModel.setDate(date = it) },
            onTimeSelected = { viewModel.setTime(time = it) },
            modifier = Modifier.weight(1f)
        )

        FormButtons(
            colour = DSColours.FeatureColours.Reminders.Primary,
            isEditing = isEditing,
            isEnabled = state.isValid,
            onAddOrSaveClick = {
                viewModel.addOrEditReminder()
                viewModel.clearState()
                onBack()
            },
            onCancelClick = {
                viewModel.clearState()
                onBack()
            }
        )
    }
}

@Composable
fun RemindersFormContent(
    draftReminder: Reminder,
    onMessageChange: (String) -> Unit,
    onDateSelected: (LocalDate) -> Unit,
    onTimeSelected: (LocalTime) -> Unit,
    modifier: Modifier = Modifier
) {
    val strings = LocalizedStrings.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        TextFieldForm(
            label = strings.message,
            initialValue = draftReminder.message,
            onValueChange = onMessageChange,
            colour = DSColours.FeatureColours.Reminders.Primary
        )

        DatePickerForm(
            date = draftReminder.date,
            onDateSelected = onDateSelected,
            colour = DSColours.FeatureColours.Reminders.Primary
        )

        TimePickerForm(
            time = draftReminder.time,
            onTimeSelected = onTimeSelected,
            colour = DSColours.FeatureColours.Reminders.Primary
        )
    }
}