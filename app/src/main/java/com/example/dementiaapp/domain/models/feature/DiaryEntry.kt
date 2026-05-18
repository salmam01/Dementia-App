package com.example.dementiaapp.domain.models.feature

import java.time.LocalDate
import java.time.LocalTime

data class DiaryEntry(
    val id: String,
    val title: String,
    val content: String,
    val date: LocalDate,
    val time: LocalTime,
    val images: List<String>?= emptyList()
)