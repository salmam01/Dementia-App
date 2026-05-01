package com.example.dementiaapp.domain.models

import java.time.LocalDate
import java.time.LocalTime

data class Medication(
    val id: String,
    val name: String,
    val dose: String,
    val type: MedicationType,
    val takeAt: LocalTime,
    val startDate: LocalDate = LocalDate.now(),
    val repeat: RepetitionType,
    val notes: String? = null,
    val completed: Boolean
)

enum class MedicationType {
    Capsule,
    Cream,
    Drops,
    Injection,
    Liquid,
    Pill
}

enum class RepetitionType {
    DAILY,
    EVERY_2_DAYS,
    WEEKLY,
    NONE
}

fun RepetitionType.toUIString(): String {
    return when (this) {
        RepetitionType.DAILY -> "Daily"
        RepetitionType.EVERY_2_DAYS -> "Every 2 Days"
        RepetitionType.WEEKLY -> "Weekly"
        RepetitionType.NONE -> "No"
    }
}