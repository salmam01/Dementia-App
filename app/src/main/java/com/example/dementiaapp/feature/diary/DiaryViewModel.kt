package com.example.dementiaapp.feature.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dementiaapp.domain.configuration.PermissionsManager
import com.example.dementiaapp.domain.models.DiaryEntry
import com.example.dementiaapp.domain.state.DateStateHolder
import com.example.dementiaapp.repository.features.DiaryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import java.time.LocalDate

class DiaryViewModel(
    private val diaryRepository: DiaryRepository,
    private val dateStateHolder: DateStateHolder,
    private val permissionsManager: PermissionsManager
): ViewModel() {
    private val _state = MutableStateFlow(DiaryState(
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
                getDiaryEntry(date)
            }
            .launchIn(viewModelScope)
    }

    private fun getDiaryEntry(date: LocalDate) {
        val result = diaryRepository.getEntryForDate(date)
        _state.update { it.copy(diaryEntry = result) }
    }

    fun addDiaryEntry(entry: DiaryEntry) {

    }

    fun editDiaryEntry(entry: DiaryEntry) {
        diaryRepository.editEntry(entry)
        _state.update { it.copy(diaryEntry = entry) }
    }

    fun deleteDiaryEntry(entry: DiaryEntry) {
        diaryRepository.deleteEntry(entry)
        _state.update { it.copy(
            diaryEntry = null,
            showConfirmationDialog = false
        ) }
    }

    fun showConfirmationDialog() {
        _state.update { it.copy(showConfirmationDialog = true) }
    }

    fun hideConfirmationDialog() {
        _state.update { it.copy(showConfirmationDialog = false) }
    }

    fun nextDay() = dateStateHolder.nextDay()
    fun previousDay() = dateStateHolder.previousDay()
}