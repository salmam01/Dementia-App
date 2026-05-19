package com.example.dementiaapp.feature.medication.shared.form

import androidx.lifecycle.ViewModel
import com.example.dementiaapp.domain.models.feature.Medication
import com.example.dementiaapp.domain.models.feature.MedicationType
import com.example.dementiaapp.domain.models.feature.RepetitionType
import com.example.dementiaapp.repository.features.MedicationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class MedicationFormViewModel(
    private val medicationRepository: MedicationRepository
): ViewModel() {
    private val _state = MutableStateFlow(MedicationFormState())
    val state = _state.asStateFlow()

    fun getMedicationById(id: String) {
        val result = medicationRepository.getMedicationById(id)
        if (result != null) {
            setSelectedMedication(result)
        }
    }

    fun addOrEditMedication() {
        if (state.value.isValid) {
            if (state.value.isEditing) {
                medicationRepository.editMedication(state.value.draftMedication)
            } else {
                medicationRepository.addMedication(state.value.draftMedication)
            }
        }
    }

    fun setSelectedMedication(medication: Medication) {
        _state.update { it.copy(
            draftMedication = medication,
            isEditing = true
        ) }
    }

    fun setName(name: String) {
        _state.update {
            val newDraft = it.draftMedication.copy(name = name)

            it.copy(
                draftMedication = newDraft,
                isValid = validateMedication(newDraft)
            )
        }
    }

    fun setType(type: MedicationType) {
        _state.update {
            val newDraft = it.draftMedication.copy(type = type)

            it.copy(
                draftMedication = newDraft,
                isValid = validateMedication(newDraft)
            )
        }
    }

    fun setDose(dose: String) {
        _state.update {
            val newDraft = it.draftMedication.copy(dose = dose)

            it.copy(
                draftMedication = newDraft,
                isValid = validateMedication(newDraft)
            )
        }
    }

    fun setTakeAt(takeAt: LocalTime) {
        _state.update {
            val newDraft = it.draftMedication.copy(takeAt = takeAt)

            it.copy(
                draftMedication = newDraft,
                isValid = validateMedication(newDraft)
            )
        }
    }

    fun setStartDate(startDate: LocalDate) {
        _state.update {
            val newDraft = it.draftMedication.copy(startDate = startDate)

            it.copy(
                draftMedication = newDraft,
                isValid = validateMedication(newDraft)
            )
        }
    }

    fun setRepetition(repetition: RepetitionType) {
        _state.update {
            val newDraft = it.draftMedication.copy(repeat = repetition)

            it.copy(
                draftMedication = newDraft,
                isValid = validateMedication(newDraft)
            )
        }
    }


    fun setNotes(notes: String) {
        _state.update {
            val newDraft = it.draftMedication.copy(notes = notes)

            it.copy(
                draftMedication = newDraft,
                isValid = validateMedication(newDraft)
            )
        }
    }

    private fun validateMedication(medication: Medication): Boolean {
        return medication.name.isNotBlank() &&
                medication.dose.isNotBlank()
//                validateDateTime(medication.startDate, medication.takeAt)
    }

    fun validateDateTime(
        date: LocalDate,
        time: LocalTime
    ): Boolean {
        val selected = date.atTime(time)
        val now = LocalDateTime.now()

        return !selected.isBefore(now)
    }

    fun clearState() {
        _state.update { it.copy(
            isEditing = false,
            draftMedication = Medication(
                id = "",
                name = "",
                dose = "",
                type = MedicationType.Capsule,
                takeAt = LocalTime.now(),
                startDate = LocalDate.now(),
                repeat = RepetitionType.NONE,
                notes = "",
                completed = false
            ),
            isValid = false
        ) }
    }
}