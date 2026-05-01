package com.example.dementiaapp.features.medication.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.domain.models.FeatureAction
import com.example.dementiaapp.domain.models.Medication
import com.example.dementiaapp.domain.time.TimeProvider.toDayTime
import com.example.dementiaapp.features.components.DateSection
import com.example.dementiaapp.features.components.DayTimeSection
import com.example.dementiaapp.features.components.buttons.AddButton
import com.example.dementiaapp.features.medication.components.MedicationItem
import com.example.dementiaapp.util.time.DayTime
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MedicationScreen(
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: MedicationViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            DateSection(
                date = state.selectedDate,
                onPreviousClick = { viewModel.previousDay() },
                onNextClick = { viewModel.nextDay() },
            )

            MedicationList(
                state.medications,
                canEdit = viewModel.hasAccessToAction(FeatureAction.EDIT),
                canDelete = viewModel.hasAccessToAction(FeatureAction.DELETE),
                onItemCompleted = { item ->
                    viewModel.setItemAsCompleted(item = item)
                }
            )
        }
        AddButton(
            text = "All Medications",
            icon = Icons.Filled.RemoveRedEye,
            colour = DSColours.FeatureColours.Medication.Primary,
            onAddClick = onNavigate,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )

    }
}

@Composable
fun MedicationList(
    medications: List<Medication>?,
    canEdit: Boolean,
    canDelete: Boolean,
    onItemCompleted: (Medication) -> Unit,
    modifier: Modifier = Modifier
) {
    val sortedMedications = medications?.sortedBy { it.takeAt }

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
        val grouped = medications
            ?.sortedBy { it.takeAt }
            ?.groupBy { it.takeAt.toDayTime() }
            ?: emptyMap()


        DayTime.entries.forEach { dayTime ->
            val items = grouped[dayTime].orEmpty()

            if (items.isNotEmpty()) {
                DayTimeSection(dayTime = dayTime)

                items.forEach { item ->
                    MedicationItem(
                        item,
                        hasCheckBox = true,
                        canEdit = canEdit,
                        canDelete = canDelete,
                        onItemCompleted = { onItemCompleted(item) },
                        onEditClick = { },
                        onDeleteClick = { }
                    )
                }
            }
        }
    }
}