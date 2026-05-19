package com.example.dementiaapp.feature.medication.shared

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dementiaapp.domain.models.FeatureAction
import com.example.dementiaapp.domain.models.FeatureType
import com.example.dementiaapp.domain.models.feature.Medication
import com.example.dementiaapp.domain.state.AppStateHolder
import com.example.dementiaapp.domain.state.DateStateHolder
import com.example.dementiaapp.repository.features.MedicationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import java.time.LocalDate

class MedicationViewModel(
    private val medicationRepository: MedicationRepository,
    private val dateStateHolder: DateStateHolder,
    private val appStateHolder: AppStateHolder
): ViewModel() {
    private val _state = MutableStateFlow(MedicationState(
        selectedDate = dateStateHolder.selectedDate.value
    ))
    val state = _state.asStateFlow()

    init {
        getAllMedications()
        observeSelectedDate()
    }

    private fun observeSelectedDate() {
        dateStateHolder.returnToToday()
        dateStateHolder.selectedDate
            .onEach { date ->
                _state.update { it.copy(selectedDate = date) }
                getMedications(date)
            }
            .launchIn(viewModelScope)
    }

    private fun getMedications(date: LocalDate) {
        val result = medicationRepository.getMedicationsForDate(date)
        _state.update { it.copy(medications = result) }
    }

    private fun getAllMedications() {
        val result = medicationRepository.getMedications()
        _state.update { it.copy(allMedications = result) }
    }

    fun hasAccessToAction(
        action: FeatureAction
    ): Boolean {
        return appStateHolder.appState.value.permissionsPolicy.canPerform(
            action = action,
            featureType = FeatureType.MEDICATION
        )
    }

    fun deleteMedication(medication: Medication) {
        medicationRepository.deleteMedication(medication)
        _state.update { it.copy(
            selectedMedication = null,
            showConfirmationDialog = false
        ) }
        getAllMedications()
        getMedications(state.value.selectedDate)
    }

    fun setItemAsCompleted(item: Medication) {
        _state.update { state ->
            state.copy(
                medications = state.medications?.map { medication ->
                    if (medication.id == item.id) {
                        medication.copy(completed = !item.completed)
                    } else {
                        medication
                    }
                }
            )
        }
    }

    fun setSelectedMedication(medication: Medication) {
        _state.update { it.copy(selectedMedication = medication) }
    }

    fun showConfirmationDialog() {
        _state.update { it.copy(
            showConfirmationDialog = true
        ) }
    }

    fun hideConfirmationDialog() {
        _state.update { it.copy(
            showConfirmationDialog = false
        ) }
    }

    fun nextDay() = dateStateHolder.nextDay()
    fun previousDay() = dateStateHolder.previousDay()

    fun refresh() {
        getMedications(state.value.selectedDate)
    }
}