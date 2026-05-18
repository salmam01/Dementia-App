package com.example.dementiaapp.domain.models.feature

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import java.time.LocalTime

data class CalendarEntry(
    val id: String,
    val icon: ImageVector,
    val title: String,
    val time: LocalTime,
    val type: CalendarFeatureTypes,
    val colour: Color
)

enum class CalendarFeatureTypes {
    DIARY,
    MEDICATION,
    REMINDERS
}