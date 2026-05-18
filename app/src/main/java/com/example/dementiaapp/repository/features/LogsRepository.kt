package com.example.dementiaapp.repository.features

import com.example.dementiaapp.domain.models.FeatureType
import com.example.dementiaapp.domain.models.feature.LogEntry
import com.example.dementiaapp.domain.models.feature.LogEntryType
import java.time.LocalDate
import java.time.LocalTime

interface LogsRepository {
    fun getLogs(): List<LogEntry>
    fun getLogsForDate(date: LocalDate): List<LogEntry>
    fun getLogById(id: String): LogEntry?

    fun addLog(log: LogEntry)
    fun editLog(log: LogEntry)
    fun deleteLog(log: LogEntry)
}

class LogsRepositoryImpl: LogsRepository {
    override fun getLogs(): List<LogEntry> {
        return logsList
    }

    override fun getLogsForDate(date: LocalDate): List<LogEntry> {
        return logsList.filter { logs ->
            logs.date.isEqual(date)
        }
    }

    override fun getLogById(id: String): LogEntry? {
        return logsList.firstOrNull { log ->
            log.id == id
        }
    }

    override fun addLog(log: LogEntry) {
        val newId = (logsList.maxOfOrNull { it.id.toInt() } ?: 0) + 1
        val newLog = log.copy(id = newId.toString())

        logsList.add(newLog)
    }

    override fun editLog(log: LogEntry) {
        val index = logsList.indexOfFirst { it.id == log.id }
        if (index != -1) {
            logsList[index] = log
        }
    }

    override fun deleteLog(log: LogEntry) {
        logsList.removeAll { it.id == log.id }
    }

    private val logsList = mutableListOf(
        LogEntry(
            id = "1",
            entryId = "med_1",
            date = LocalDate.now(),
            time = LocalTime.of(8, 0),
            content = "Morning blood pressure medication taken successfully.",
            featureType = FeatureType.MEDICATION,
            logEntryType = LogEntryType.COMPLETED
        ),

        LogEntry(
            id = "2",
            entryId = "med_2",
            date = LocalDate.now(),
            time = LocalTime.of(9, 30),
            content = "Vitamin D tablet was missed.",
            featureType = FeatureType.MEDICATION,
            logEntryType = LogEntryType.MISSED
        ),

        LogEntry(
            id = "3",
            entryId = "rem_1",
            date = LocalDate.now(),
            time = LocalTime.of(10, 15),
            content = "Reminder completed: Drink a glass of water.",
            featureType = FeatureType.REMINDERS,
            logEntryType = LogEntryType.COMPLETED
        ),

        LogEntry(
            id = "4",
            entryId = "dia_1",
            date = LocalDate.now().minusDays(1),
            time = LocalTime.of(14, 45),
            content = "Diary entry added about visiting the local park.",
            featureType = FeatureType.DIARY,
            logEntryType = LogEntryType.ADDED
        ),

        LogEntry(
            id = "5",
            entryId = "fam_1",
            date = LocalDate.now().minusDays(1),
            time = LocalTime.of(16, 20),
            content = "New family member profile added for daughter Sarah.",
            featureType = FeatureType.MY_FAMILY,
            logEntryType = LogEntryType.ADDED
        ),

        LogEntry(
            id = "6",
            entryId = "rem_2",
            date = LocalDate.now().minusDays(2),
            time = LocalTime.of(11, 0),
            content = "Reminder deleted: Afternoon stretching exercise.",
            featureType = FeatureType.REMINDERS,
            logEntryType = LogEntryType.DELETED
        ),

        LogEntry(
            id = "7",
            entryId = "dia_2",
            date = LocalDate.now().minusDays(2),
            time = LocalTime.of(18, 10),
            content = "Diary entry completed describing today's lunch outing.",
            featureType = FeatureType.DIARY,
            logEntryType = LogEntryType.COMPLETED
        ),

        LogEntry(
            id = "8",
            entryId = "med_3",
            date = LocalDate.now().minusDays(3),
            time = LocalTime.of(7, 45),
            content = "New evening medication schedule added.",
            featureType = FeatureType.MEDICATION,
            logEntryType = LogEntryType.ADDED
        )
    )
}