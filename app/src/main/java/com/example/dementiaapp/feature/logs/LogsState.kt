package com.example.dementiaapp.feature.logs

import com.example.dementiaapp.domain.models.FeatureType
import com.example.dementiaapp.domain.models.feature.LogEntry
import java.time.LocalDate

data class LogsState(
    val selectedDate: LocalDate,
    val logs: List<LogEntry> = emptyList(),

    val summaryExtended: Boolean = false,
    val filteredLogs: List<LogEntry> = emptyList(),
    val appliedFilters: List<FeatureType> = emptyList()
)
