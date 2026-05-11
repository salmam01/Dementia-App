package com.example.dementiaapp.feature.diary

import com.example.dementiaapp.domain.models.DiaryEntry
import java.time.LocalDate

data class DiaryState(
    val diaryEntry: DiaryEntry ?= null,
    val selectedDate: LocalDate,
    val showConfirmationDialog: Boolean = false
)
