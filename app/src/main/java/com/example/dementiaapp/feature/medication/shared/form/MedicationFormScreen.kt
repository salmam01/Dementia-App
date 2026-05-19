package com.example.dementiaapp.feature.medication.shared.form

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.ReplayCircleFilled
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.domain.models.feature.Medication
import com.example.dementiaapp.domain.models.feature.MedicationType
import com.example.dementiaapp.domain.models.feature.RepetitionType
import com.example.dementiaapp.feature.components.buttons.FormButtons
import com.example.dementiaapp.feature.components.TitleSection
import com.example.dementiaapp.feature.components.form.DatePickerForm
import com.example.dementiaapp.feature.components.form.DropDownForm
import com.example.dementiaapp.feature.components.form.TextFieldForm
import com.example.dementiaapp.feature.components.form.TimePickerForm
import com.example.dementiaapp.feature.medication.shared.toDomain
import com.example.dementiaapp.feature.medication.shared.toUI
import com.example.dementiaapp.localization.LocalizedStrings
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun MedicationFormScreen(
    medicationId: String?,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: MedicationFormViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val strings = LocalizedStrings.current

    LaunchedEffect(medicationId) {
        viewModel.clearState()
        if (medicationId != null) {
            viewModel.getMedicationById(id = medicationId)
        }
    }

    val isEditing = state.isEditing
    val title =
        if (isEditing) "${strings.edit} ${state.draftMedication?.name}"
        else "${strings.add} ${strings.medication}"
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
            colour = DSColours.FeatureColours.Medication.Primary
        )

        MedicationFormContent(
            draftMedication = state.draftMedication,
            onNameChange = { viewModel.setName(it) },
            onDoseChange = { viewModel.setDose(it) },
            onTypeChange = { viewModel.setType(it) },
            onStartDateSelected = { viewModel.setStartDate(it) },
            onTakeAtSelected = { viewModel.setTakeAt(it) },
            onRepeatChange = { viewModel.setRepetition(it) },
            onNotesChange = { viewModel.setNotes(it) },
            modifier = Modifier.weight(1f)
        )

        FormButtons(
            colour = DSColours.FeatureColours.Medication.Primary,
            isEditing = isEditing,
            isEnabled = state.isValid,
            onAddOrSaveClick = {
                viewModel.addOrEditMedication()
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
fun MedicationFormContent(
    draftMedication: Medication,
    onNameChange: (String) -> Unit,
    onDoseChange: (String) -> Unit,
    onTypeChange: (MedicationType) -> Unit,
    onStartDateSelected: (LocalDate) -> Unit,
    onTakeAtSelected: (LocalTime) -> Unit,
    onRepeatChange: (RepetitionType) -> Unit,
    onNotesChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val strings = LocalizedStrings.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TextFieldForm(
            label = strings.name,
            initialValue = draftMedication.name,
            onValueChange = onNameChange,
            colour = DSColours.FeatureColours.Medication.Primary
        )

        val medicationTypeUI = MedicationType.entries.map { it.toUI() }

        DropDownForm(
            options = medicationTypeUI,
            selectedOption = medicationTypeUI.first { it.name == draftMedication.type.name },
            label = strings.type,
            colour = DSColours.FeatureColours.Medication.Primary,
            optionText = { it.name },
            optionIcon = { it.icon },
            onOptionSelected = { onTypeChange(it.toDomain()) },
            leadingIcon = medicationTypeUI.first { it.name == draftMedication.type.name }.icon
        )

        TextFieldForm(
            label = strings.dose,
            initialValue = draftMedication.dose,
            onValueChange = onDoseChange,
            colour = DSColours.FeatureColours.Medication.Primary
        )

        DatePickerForm(
            date = draftMedication.startDate,
            onDateSelected = onStartDateSelected,
            colour = DSColours.FeatureColours.Medication.Primary
        )

        TimePickerForm(
            time = draftMedication.takeAt,
            onTimeSelected = onTakeAtSelected,
            colour = DSColours.FeatureColours.Medication.Primary
        )

        DropDownForm(
            options = RepetitionType.entries,
            selectedOption = draftMedication.repeat,
            onOptionSelected = { onRepeatChange(it) },

            label = strings.repeat,
            colour = DSColours.FeatureColours.Medication.Primary,
            leadingIcon = Icons.Rounded.ReplayCircleFilled,
            optionText = { it.toString() }
        )

        TextFieldForm(
            label = strings.notes,
            initialValue = draftMedication.notes ?: "",
            onValueChange = onNotesChange,
            colour = DSColours.FeatureColours.Medication.Primary
        )
    }
}