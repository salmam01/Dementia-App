package com.example.dementiaapp.feature.calendar

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import java.time.LocalDate
import java.time.LocalTime

data class CalendarState(
    val today: LocalDate,
    val selectedDate: LocalDate,

    val calendarEntries: List<CalendarEntry> = emptyList(),
    val filteredCalendarEntries: List<CalendarEntry> = emptyList(),

    // all accessible features
    val calendarFeatureTypes: List<CalendarFeatureTypes> = emptyList(),
    // all applied filters based on accessible features
    val appliedFilters: List<CalendarFeatureTypes> = emptyList(),

    val showFilterMenu: Boolean = false
)

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