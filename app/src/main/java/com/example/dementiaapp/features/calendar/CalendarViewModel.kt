package com.example.dementiaapp.features.calendar

import androidx.lifecycle.ViewModel
import com.example.dementiaapp.domain.state.DateStateHolder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CalendarViewModel(
    private val dateStateHolder: DateStateHolder,
): ViewModel() {
}