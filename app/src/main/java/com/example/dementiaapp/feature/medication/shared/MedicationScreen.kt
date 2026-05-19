package com.example.dementiaapp.feature.medication.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.domain.models.DayTime
import com.example.dementiaapp.domain.models.FeatureAction
import com.example.dementiaapp.domain.models.feature.Medication
import com.example.dementiaapp.domain.time.TimeProvider.toDayTime
import com.example.dementiaapp.feature.components.time.DatePickerSection
import com.example.dementiaapp.feature.components.time.DayTimeSection
import com.example.dementiaapp.feature.components.buttons.StickyActionButton
import com.example.dementiaapp.feature.medication.components.MedicationItem
import com.example.dementiaapp.localization.LocalizedStrings
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MedicationScreen(
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: MedicationViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val strings = LocalizedStrings.current

    LaunchedEffect(Unit) {
        viewModel.refresh()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            DatePickerSection(
                date = state.selectedDate,
                onPreviousClick = { viewModel.previousDay() },
                onNextClick = { viewModel.nextDay() },
            )

            Spacer(modifier = Modifier.height(DSDimensions.Space4))

            MedicationList(
                state.medications,
                canEdit = false,
                canDelete = false,
                onItemCompleted = { item ->
                    viewModel.setItemAsCompleted(item = item)
                }
            )
        }
        StickyActionButton(
            text = strings.allMedications,
            icon = Icons.Filled.RemoveRedEye,
            colour = DSColours.FeatureColours.Medication.Primary,
            onClick = onNavigate,
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
    Column(
        verticalArrangement = Arrangement.spacedBy(DSDimensions.Space4),
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(
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