package com.example.dementiaapp.feature.diary.form

import com.example.dementiaapp.domain.models.feature.DiaryEntry
import java.time.LocalDate
import java.time.LocalTime

data class DiaryFormState(
    val isEditing: Boolean = false,
    val selectedEntry: DiaryEntry ?= null,
    val draftEntry: DiaryEntry = DiaryEntry(
        id = "",
        title = "",
        content = "",
        date = LocalDate.now(),
        time = LocalTime.now(),
        images = emptyList()
    ),
    val isValid: Boolean = false
)
