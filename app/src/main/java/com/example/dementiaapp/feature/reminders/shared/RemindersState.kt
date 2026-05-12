package com.example.dementiaapp.feature.reminders.shared

import com.example.dementiaapp.domain.models.Reminder
import java.time.LocalDate

data class RemindersState(
    val reminders: List<Reminder> ?= emptyList(),
    val selectedDate: LocalDate,
    val selectedReminder: Reminder?= null,
    val showConfirmationDialog: Boolean = false
)