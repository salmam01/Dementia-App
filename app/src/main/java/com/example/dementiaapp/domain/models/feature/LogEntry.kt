package com.example.dementiaapp.domain.models.feature

import com.example.dementiaapp.domain.models.FeatureType
import java.time.LocalDate
import java.time.LocalTime

data class LogEntry(
    val id: String,
    val entryId: String,
    val date: LocalDate,
    val time: LocalTime,
    val content: String,
    val featureType: FeatureType,
    val logEntryType: LogEntryType
)

enum class LogEntryType {
    COMPLETED,
    MISSED,
    ADDED,
    DELETED
}