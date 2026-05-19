package com.example.dementiaapp.feature.medication.shared

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Vaccines
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.dementiaapp.domain.models.feature.MedicationType

data class MedicationTypeUI(
    val name: String,
    val icon: ImageVector
)

fun MedicationType.toUI(): MedicationTypeUI {
    return when (this) {
        MedicationType.Capsule -> {
            MedicationTypeUI(
                "Capsule",
                icon = Icons.Filled.Medication
            )
        }
        MedicationType.Cream -> {
            MedicationTypeUI(
                "Cream",
                icon = Icons.Filled.Spa
            )
        }
        MedicationType.Drops -> {
            MedicationTypeUI(
                "Drops",
                icon = Icons.Filled.WaterDrop
            )
        }
        MedicationType.Injection -> {
            MedicationTypeUI(
                "Injection",
                icon = Icons.Filled.Vaccines
            )
        }
        MedicationType.Liquid -> {
            MedicationTypeUI(
                "Liquid",
                icon = Icons.Filled.LocalDrink
            )
        }
        MedicationType.Pill -> {
            MedicationTypeUI(
                "Pill",
                icon = Icons.Filled.Medication
            )
        }
    }
}

fun MedicationTypeUI.toDomain(): MedicationType =
    when (name) {
        "Capsule" -> MedicationType.Capsule
        "Cream" -> MedicationType.Cream
        "Drops" -> MedicationType.Drops
        "Injection" -> MedicationType.Injection
        "Liquid" -> MedicationType.Liquid
        "Pill" -> MedicationType.Pill
        else -> MedicationType.Pill
    }