package com.example.dementiaapp.feature.reminders.shared

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dementiaapp.domain.configuration.PermissionsManager
import com.example.dementiaapp.domain.models.FeatureAction
import com.example.dementiaapp.domain.models.FeatureType
import com.example.dementiaapp.domain.models.Reminder
import com.example.dementiaapp.domain.state.DateStateHolder
import com.example.dementiaapp.domain.state.UserStateHolder
import com.example.dementiaapp.feature.reminders.shared.RemindersState
import com.example.dementiaapp.repository.UserRepository
import com.example.dementiaapp.repository.features.RemindersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import java.time.LocalDate

class RemindersViewModel(
    private val userStateHolder: UserStateHolder,
    private val dateStateHolder: DateStateHolder,
    private val remindersRepository: RemindersRepository,
    private val permissionsManager: PermissionsManager
): ViewModel() {
    private val _state = MutableStateFlow(
        RemindersState(
            selectedDate = dateStateHolder.selectedDate.value
        )
    )
    val state = _state.asStateFlow()
    val user = userStateHolder.currentUser

    init {
        observeSelectedDate()
    }

    private fun observeSelectedDate() {
        dateStateHolder.returnToToday()
        dateStateHolder.selectedDate
            .onEach { date ->
                _state.update { it.copy(selectedDate = date) }
                getReminders(date)
            }
            .launchIn(viewModelScope)
    }

    fun hasAccessToAction(
        action: FeatureAction
    ): Boolean {
        return permissionsManager.hasPermission(
            action = action,
            featureType = FeatureType.REMINDERS
        )
    }

    fun getReminders(date: LocalDate) {
        val result = remindersRepository.getRemindersForDate(date = date)
            .sortedBy { it.time }
        _state.update { it.copy(reminders = result) }
    }

    fun setSelectedReminder(reminder: Reminder) {
        _state.update { it.copy(selectedReminder = reminder) }
    }

    fun deleteReminder(reminder: Reminder) {
        remindersRepository.deleteReminder(reminder)
        _state.update { it.copy(selectedReminder = null) }
        getReminders(state.value.selectedDate)
    }

    fun setItemCompleted(item: Reminder) {
        _state.update { state ->
            state.copy(
                reminders = state.reminders?.map { reminder ->
                    if (reminder.id == item.id) {
                        reminder.copy(completed = !item.completed)
                    } else {
                        reminder
                    }
                }
            )
        }
    }

    fun showConfirmationDialog() {
        _state.update { it.copy(
            showConfirmationDialog = true
        ) }
    }

    fun hideConfirmationDialog() {
        _state.update { it.copy(
            showConfirmationDialog = false
        ) }
    }

    fun refresh() {
        getReminders(state.value.selectedDate)
    }

    fun nextDay() = dateStateHolder.nextDay()
    fun previousDay() = dateStateHolder.previousDay()

}