package com.example.dementiaapp.features.medication.manage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.domain.models.Medication
import com.example.dementiaapp.features.components.FormButtons
import com.example.dementiaapp.features.components.TitleSection
import org.koin.androidx.compose.koinViewModel

@Composable
fun ManageMedicationScreen(
    selectedMedicationId: String?,
    modifier: Modifier = Modifier
) {
    val viewModel: ManageMedicationViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    if (selectedMedicationId != null) {
        viewModel.getMedicationById(id = selectedMedicationId)
    }

    val isEditing = state.isEditing
    val title =
        if (isEditing) "Edit ${state.selectedMedication?.name}"
        else "Add Medication"

    val icon =
        if (isEditing) Icons.Rounded.Edit
        else Icons.Rounded.Add

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TitleSection(
            title = title,
            icon = icon,
            colour = DSColours.FeatureColours.Medication.Primary
        )

        FormButtons(
            colour = DSColours.FeatureColours.Medication.Primary,
            isEditing = isEditing,
            isEnabled = true,
            onAddOrSaveClick = { },
            onCancelClick = { }
        )
    }
}

@Composable
fun MedicationFieldList(
    medication: Medication,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

    }
}

@Composable
fun MedicationField(
    label: String,
    text: String?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = label,

        )
    }
}