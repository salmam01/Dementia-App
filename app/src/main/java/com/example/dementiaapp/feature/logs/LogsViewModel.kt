package com.example.dementiaapp.feature.logs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dementiaapp.domain.state.DateStateHolder
import com.example.dementiaapp.repository.features.LogsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import java.time.LocalDate

class LogsViewModel(
    val dateStateHolder: DateStateHolder,
    val logsRepository: LogsRepository
): ViewModel() {
    private val _state = MutableStateFlow(LogsState(
        selectedDate = dateStateHolder.selectedDate.value
    ))
    val state = _state.asStateFlow()

    init {
        observeSelectedDate()
    }

    private fun observeSelectedDate() {
        dateStateHolder.returnToToday()
        dateStateHolder.selectedDate
            .onEach { date ->
                _state.update { it.copy(selectedDate = date) }
                getLogs(date)
            }
            .launchIn(viewModelScope)
    }

    fun getLogs(date: LocalDate) {
        val result = logsRepository.getLogsForDate(date = date)
            .sortedBy { it.time }
        _state.update { it.copy(logs = result) }
    }

    fun toggleSummary() {
        val isExtended = state.value.summaryExtended
        _state.update { it.copy(summaryExtended = !isExtended) }
    }

    fun refresh() {
        getLogs(date = state.value.selectedDate)
    }
}