package com.example.dementiaapp.features.reminders.manage

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
import com.example.dementiaapp.features.components.FormButtons
import com.example.dementiaapp.features.components.TitleSection
import com.example.dementiaapp.features.components.form.DatePickerForm
import com.example.dementiaapp.features.components.form.TextFieldForm
import com.example.dementiaapp.features.components.form.TimePickerForm
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun ManageRemindersScreen(
    selectedReminderId: String?,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: ManageRemindersViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(selectedReminderId) {
        viewModel.clearState()
        if (selectedReminderId != null) {
            viewModel.getReminderById(id = selectedReminderId)
        }
    }

    val isEditing = state.isEditing
    val title =
        if (isEditing) "Edit Reminder"
        else "Add Reminder"

    val icon =
        if (isEditing) Icons.Rounded.Edit
        else Icons.Rounded.Add

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        TitleSection(
            title = title,
            icon = icon,
            colour = DSColours.FeatureColours.Reminders.Primary
        )

        ManageRemindersForm(
            draftReminder = state.draftReminder,
            onMessageChange = { viewModel.setMessage(message = it) },
            onDateSelected = { viewModel.setDate(date = it) },
            onTimeSelected = { viewModel.setTime(time = it) }
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
fun ManageRemindersForm(
    draftReminder: Reminder,
    onMessageChange: (String) -> Unit,
    onDateSelected: (LocalDate) -> Unit,
    onTimeSelected: (LocalTime) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        TextFieldForm(
            label = "Message",
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