package com.example.dementiaapp.domain.state

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate

class DateStateHolder(
    initialDate: LocalDate = LocalDate.now()
) {
    private val _selectedDate = MutableStateFlow(initialDate)
    val selectedDate = _selectedDate.asStateFlow()

    fun setDate(date: LocalDate) {
        _selectedDate.value = date
    }

    fun nextDay() {
        _selectedDate.update { it.plusDays(1) }
    }

    fun previousDay() {
        _selectedDate.update { it.minusDays(1) }
    }

    fun returnToToday() {
        _selectedDate.value = LocalDate.now()
    }
}