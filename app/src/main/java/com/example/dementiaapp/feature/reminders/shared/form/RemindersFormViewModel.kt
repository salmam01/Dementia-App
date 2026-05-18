package com.example.dementiaapp.feature.reminders.shared.form

import androidx.lifecycle.ViewModel
import com.example.dementiaapp.domain.models.feature.Reminder
import com.example.dementiaapp.domain.state.AppStateHolder
import com.example.dementiaapp.repository.features.RemindersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class RemindersFormViewModel(
    private val appStateHolder: AppStateHolder,
    private val remindersRepository: RemindersRepository
): ViewModel() {
    private val _state = MutableStateFlow(RemindersFormState())
    val state = _state.asStateFlow()

    fun getReminderById(id: String) {
        val result = remindersRepository.getReminderById(id)
        if (result != null) {
            setSelectedReminder(result)
        }
    }

    fun addOrEditReminder() {
        if (state.value.isValid) {
            if (state.value.isEditing) {
                remindersRepository.editReminder(state.value.draftReminder)
            } else {
                remindersRepository.addReminder(state.value.draftReminder)
            }
        }
    }

    fun setSelectedReminder(reminder: Reminder) {
        _state.update { it.copy(
            isEditing = true,
            selectedReminder = reminder,
            draftReminder = reminder
        ) }
    }

    fun setMessage(message: String) {
        _state.update {
            val newDraft = it.draftReminder.copy(message = message)

            it.copy(
                draftReminder = newDraft,
                isValid = validateReminder(newDraft)
            )
        }
    }

    fun setDate(date: LocalDate) {
        _state.update {
            val newDraft = it.draftReminder.copy(date = date)

            it.copy(
                draftReminder = newDraft,
                isValid = validateReminder(newDraft)
            )
        }
    }

    fun setTime(time: LocalTime) {
        _state.update {
            val newDraft = it.draftReminder.copy(time = time)

            it.copy(
                draftReminder = newDraft,
                isValid = validateReminder(newDraft)
            )
        }
    }

    fun setFrom() {
        _state.update {
            val newDraft = it.draftReminder.copy(from = appStateHolder.appState.value.user.name)
            it.copy(
                draftReminder = newDraft,
                isValid = validateReminder(newDraft)
            )
        }
    }

    private fun validateReminder(reminder: Reminder): Boolean {
        return reminder.message.isNotBlank() &&
                reminder.from.isNotBlank() &&
                validateDateTime(reminder.date, reminder.time)
    }

    fun validateDateTime(
        date: LocalDate,
        time: LocalTime
    ): Boolean {
        val selected = date.atTime(time)
        val now = LocalDateTime.now()

        return !selected.isBefore(now)
    }

    fun clearState() {
        _state.update { it.copy(
            isEditing = false,
            isValid = false,
            selectedReminder = null,
            draftReminder = Reminder(
                id = "",
                message = "",
                date = LocalDate.now(),
                time = LocalTime.now().plusHours(1),
                from = appStateHolder.appState.value.user.name.substringBefore(" "),
                completed = false
            )
        ) }
    }
}