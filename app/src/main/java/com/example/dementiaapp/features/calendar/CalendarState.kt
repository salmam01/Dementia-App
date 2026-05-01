package com.example.dementiaapp.features.calendar

import java.time.LocalDate

data class CalendarState(
    val today: LocalDate ?= null,
    val selectedDate: LocalDate ?= null
)