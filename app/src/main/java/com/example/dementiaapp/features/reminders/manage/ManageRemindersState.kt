package com.example.dementiaapp.features.reminders.manage

import com.example.dementiaapp.domain.models.Reminder
import java.time.LocalDate
import java.time.LocalTime

data class ManageRemindersState(
    val isEditing: Boolean = false,
    val selectedReminder: Reminder ?= null,
    val draftReminder: Reminder = Reminder(
        id = "",
        message = "",
        date = LocalDate.now(),
        time = LocalTime.now().plusHours(1),
        from = "Me",
        completed = false
    ),
    val isValid: Boolean = false
)
