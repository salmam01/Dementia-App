package com.example.dementiaapp.features.medication.manage

import com.example.dementiaapp.domain.models.Medication

data class ManageMedicationState(
    val isEditing: Boolean = false,
    val selectedMedication: Medication ?= null
)