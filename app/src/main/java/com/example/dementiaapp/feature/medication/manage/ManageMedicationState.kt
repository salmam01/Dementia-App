package com.example.dementiaapp.feature.medication.manage

import com.example.dementiaapp.domain.models.Medication

data class ManageMedicationState(
    val isEditing: Boolean = false,
    val selectedMedication: Medication ?= null
)