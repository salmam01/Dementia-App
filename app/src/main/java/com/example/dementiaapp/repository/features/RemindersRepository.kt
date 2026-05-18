package com.example.dementiaapp.repository.features

import com.example.dementiaapp.domain.models.feature.Reminder
import java.time.LocalDate
import java.time.LocalTime

interface RemindersRepository {
    fun getReminders(): List<Reminder>
    fun getRemindersForDate(date: LocalDate): List<Reminder>
    fun getReminderById(id: String): Reminder?

    fun addReminder(reminder: Reminder)
    fun editReminder(reminder: Reminder)
    fun deleteReminder(reminder: Reminder)
}

class RemindersRepositoryImpl: RemindersRepository {
    override fun getReminders(): List<Reminder> {
        return remindersList
    }

    override fun getRemindersForDate(date: LocalDate): List<Reminder> {
        return remindersList.filter { reminder ->
            reminder.date.isEqual(date)
        }
    }

    override fun getReminderById(id: String): Reminder? {
        return remindersList.firstOrNull { it.id == id }
    }

    override fun addReminder(reminder: Reminder) {
        val newId = (remindersList.maxOfOrNull { it.id.toInt() } ?: 0) + 1
        val newReminder = reminder.copy(id = newId.toString())

        remindersList.add(newReminder)
    }

    override fun editReminder(reminder: Reminder) {
        val index = remindersList.indexOfFirst { it.id == reminder.id }
        if (index != -1) {
            remindersList[index] = reminder
        }
    }

    override fun deleteReminder(reminder: Reminder) {
        remindersList.removeAll { it.id == reminder.id }
    }

    private val remindersList = mutableListOf(
        Reminder(
            id = "1",
            message = "Brush your teeth",
            date = LocalDate.now(),
            time = LocalTime.of(8, 25),
            from = "Linda",
            completed = true
        ),
        Reminder(
            id = "2",
            message = "Eat Breakfast",
            date = LocalDate.now(),
            time = LocalTime.of(9, 0),
            from = "Me",
            completed = true
        ),
        Reminder(
            id = "3",
            message = "Drink water",
            date = LocalDate.now(),
            time = LocalTime.of(15, 0),
            from = "Linda",
            completed = false
        )
    )
}