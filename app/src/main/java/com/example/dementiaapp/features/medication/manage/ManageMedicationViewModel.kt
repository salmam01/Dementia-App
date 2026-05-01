package com.example.dementiaapp.features.medication.manage

import androidx.lifecycle.ViewModel
import com.example.dementiaapp.domain.models.Medication
import com.example.dementiaapp.repository.features.MedicationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ManageMedicationViewModel(
    private val medicationRepository: MedicationRepository
): ViewModel() {
    private val _state = MutableStateFlow(ManageMedicationState())
    val state = _state.asStateFlow()

    fun getMedicationById(id: String) {
        val result = medicationRepository.getMedicationById(id)
        if (result != null) {
            setSelectedMedication(result)
        }
    }

    fun addMedication(medication: Medication) {
        medicationRepository.addMedication(medication)
    }

    fun editMedication(medication: Medication) {
        medicationRepository.editMedication(medication)
    }

    fun setSelectedMedication(medication: Medication) {
        _state.update { it.copy(
            selectedMedication = medication,
            isEditing = true
        ) }
    }

    fun clearState() {
        _state.update { it.copy(
            isEditing = false,
            selectedMedication = null
        ) }
    }
}