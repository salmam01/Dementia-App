package com.example.dementiaapp.feature.diary.form

import androidx.lifecycle.ViewModel
import com.example.dementiaapp.domain.models.feature.DiaryEntry
import com.example.dementiaapp.domain.state.DateStateHolder
import com.example.dementiaapp.repository.features.DiaryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.LocalTime

class DiaryFormViewModel(
    private val diaryRepository: DiaryRepository,
    private val dateStateHolder: DateStateHolder
): ViewModel() {
    private val _state = MutableStateFlow(DiaryFormState())
    val state = _state.asStateFlow()

    fun getEntryById(id: String) {
        val result = diaryRepository.getEntryById(id)
        if (result != null) {
            setSelectedEntry(result)
        }
    }

    fun addOrEditEntry() {
        if (state.value.isValid) {
            if (state.value.isEditing) {
                diaryRepository.editEntry(state.value.draftEntry)
            } else {
                diaryRepository.addEntry(state.value.draftEntry)
            }
        }
    }

    fun setSelectedEntry(entry: DiaryEntry) {
        _state.update { it.copy(
            isEditing = true,
            selectedEntry = entry,
            draftEntry = entry
        ) }
    }

    fun setTitle(title: String) {
        _state.update {
            val newDraft = it.draftEntry.copy(title = title)

            it.copy(
                draftEntry = newDraft,
                isValid = validateEntry(newDraft)
            )
        }
    }

    fun setContent(content: String) {
        _state.update {
            val newDraft = it.draftEntry.copy(content = content)

            it.copy(
                draftEntry = newDraft,
                isValid = validateEntry(newDraft)
            )
        }
    }

    fun setTime(time: LocalTime) {
        _state.update {
            val newDraft = it.draftEntry.copy(time = time)

            it.copy(
                draftEntry = newDraft,
                isValid = validateEntry(newDraft)
            )
        }
    }

    fun setDate(date: LocalDate) {
        _state.update {
            val newDraft = it.draftEntry.copy(date = date)

            it.copy(
                draftEntry = newDraft,
                isValid = validateEntry(newDraft)
            )
        }
    }

    private fun validateEntry(entry: DiaryEntry): Boolean {
        return entry.title.isNotBlank() &&
                entry.content.isNotBlank()
    }

    fun clearState() {
        _state.update { it.copy(
            isEditing = false,
            isValid = false,
            selectedEntry = null,
            draftEntry = DiaryEntry(
                id = "",
                title = "",
                content = "",
                date = dateStateHolder.selectedDate.value,
                time = LocalTime.now().plusHours(1),
                images = emptyList()
            )
        ) }
    }
}