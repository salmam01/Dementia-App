package com.example.dementiaapp.feature.calendar

import com.example.dementiaapp.domain.models.feature.CalendarEntry
import com.example.dementiaapp.domain.models.feature.CalendarFeatureTypes
import java.time.LocalDate

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