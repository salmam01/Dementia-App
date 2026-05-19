package com.example.dementiaapp.feature.medication.shared.form

import com.example.dementiaapp.domain.models.feature.Medication
import com.example.dementiaapp.domain.models.feature.MedicationType
import com.example.dementiaapp.domain.models.feature.RepetitionType
import com.example.dementiaapp.feature.medication.shared.MedicationTypeUI
import java.time.LocalDate
import java.time.LocalTime

data class MedicationFormState(
    val isEditing: Boolean = false,
    val draftMedication: Medication = Medication(
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
    val isValid: Boolean = false
)