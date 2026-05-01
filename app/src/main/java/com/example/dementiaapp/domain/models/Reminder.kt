package com.example.dementiaapp.domain.models

import java.time.LocalDate
import java.time.LocalTime

data class Reminder(
    val id: String,
    val message: String,
    val date: LocalDate,
    val time: LocalTime,
    val from: String,
    val completed: Boolean
)