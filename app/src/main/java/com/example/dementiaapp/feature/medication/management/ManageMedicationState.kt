package com.example.dementiaapp.feature.medication.management

import com.example.dementiaapp.domain.models.Medication

data class ManageMedicationState(
    val isEditing: Boolean = false,
    val selectedMedication: Medication ?= null
)