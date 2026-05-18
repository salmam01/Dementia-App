package com.example.dementiaapp.feature.medication.main

import com.example.dementiaapp.domain.models.feature.Medication
import java.time.LocalDate

data class MedicationState(
    val allMedications: List<Medication> ?= emptyList(),
    val medications: List<Medication> ?= emptyList(),
    val selectedDate: LocalDate,
    val selectedMedication: Medication ?= null,
    val showConfirmationDialog: Boolean = false
)
