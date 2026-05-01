package com.example.dementiaapp.features.medication.all

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.domain.models.FeatureAction
import com.example.dementiaapp.domain.models.Medication
import com.example.dementiaapp.features.components.ConfirmationDialog
import com.example.dementiaapp.features.components.buttons.AddButton
import com.example.dementiaapp.features.medication.components.MedicationItem
import com.example.dementiaapp.features.medication.main.MedicationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AllMedicationsScreen(
    onAddOrEditMedication: (String?) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: MedicationViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val canAdd = viewModel.hasAccessToAction(FeatureAction.ADD)
    val canEdit = canAdd
            && viewModel.hasAccessToAction(FeatureAction.EDIT)
    val canDelete = canAdd && canEdit
            && viewModel.hasAccessToAction(FeatureAction.DELETE)

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AllMedicationsList(
                medications = state.allMedications,
                canEdit = canEdit,
                canDelete = canDelete,
                onEditClick = { onAddOrEditMedication(it.id) },
                onDeleteClick = {
                    viewModel.setSelectedMedication(it)
                    viewModel.showConfirmationDialog()
                }
            )
        }

        if (state.showConfirmationDialog) {
            val selectedMedication = state.selectedMedication
            if (selectedMedication != null) {
                ConfirmationDialog(
                    text = "delete \"${selectedMedication.name}\"",
                    onConfirm = { viewModel.deleteMedication(selectedMedication) },
                    onDeny = { viewModel.hideConfirmationDialog() }
                )
            }
        }
        if (canAdd) {
            AddButton(
                text = "Add Medication",
                icon = Icons.Filled.Add,
                colour = DSColours.FeatureColours.Medication.Primary,
                onAddClick = { onAddOrEditMedication(null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
fun AllMedicationsList(
    medications: List<Medication>?,
    canEdit: Boolean,
    canDelete: Boolean,
    onEditClick: (Medication) -> Unit,
    onDeleteClick: (Medication) -> Unit,
    modifier: Modifier = Modifier
) {
    val sortedMedications = medications?.sortedBy { it.name }

    Column(
        verticalArrangement = Arrangement.spacedBy(DSDimensions.Space3),
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(
                top = DSDimensions.Space2,
                start = DSDimensions.Space2,
                end = DSDimensions.Space2,
                bottom = DSDimensions.AddButtonSpace
            )
    ) {
        sortedMedications?.forEach { item ->
            MedicationItem(
                item,
                hasCheckBox = false,
                canEdit = canEdit,
                canDelete = canDelete,
                onItemCompleted = { },
                onEditClick = { onEditClick(item) },
                onDeleteClick = { onDeleteClick(item) }
            )
        }
    }
}