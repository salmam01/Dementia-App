package com.example.dementiaapp.feature.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dementiaapp.domain.configuration.PermissionsManager
import com.example.dementiaapp.domain.models.DiaryEntry
import com.example.dementiaapp.domain.models.FeatureType
import com.example.dementiaapp.domain.models.Medication
import com.example.dementiaapp.domain.models.Reminder
import com.example.dementiaapp.domain.state.DateStateHolder
import com.example.dementiaapp.feature.AllFeatureUI.DiaryUI
import com.example.dementiaapp.feature.AllFeatureUI.MedicationUI
import com.example.dementiaapp.feature.AllFeatureUI.RemindersUI
import com.example.dementiaapp.repository.features.DiaryRepository
import com.example.dementiaapp.repository.features.MedicationRepository
import com.example.dementiaapp.repository.features.RemindersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import java.time.LocalDate

class CalendarViewModel(
    private val dateStateHolder: DateStateHolder,
    private val permissionsManager: PermissionsManager,
    private val diaryRepository: DiaryRepository,
    private val medicationRepository: MedicationRepository,
    private val remindersRepository: RemindersRepository
): ViewModel() {
    private val _state = MutableStateFlow(CalendarState(
        today = LocalDate.now(),
        selectedDate = dateStateHolder.selectedDate.value
    ))
    val state = _state.asStateFlow()

    init {
        observeSelectedDate()
        getFeaturetypes()
    }

    private fun observeSelectedDate() {
        dateStateHolder.returnToToday()
        dateStateHolder.selectedDate
            .onEach { date ->
                _state.update { it.copy(selectedDate = date) }
                getAllCalendarEntries(date)
            }
            .launchIn(viewModelScope)
    }

    fun getAllCalendarEntries(date: LocalDate) {
        val featureTypes = state.value.calendarFeatureTypes

        val calendarEntries = buildList {

            if (CalendarFeatureTypes.DIARY in featureTypes) {
                val diaryEntry = getDiaryEntry(date)
                    ?.let {
                        CalendarEntry(
                            id = it.id,
                            icon = DiaryUI.icon,
                            title = it.title,
                            time = it.time,
                            colour = DiaryUI.colour,
                            type = CalendarFeatureTypes.DIARY
                        )
                    }
                if (diaryEntry != null) {
                    add(diaryEntry)
                }
            }

            if (CalendarFeatureTypes.MEDICATION in featureTypes) {
                val medicationEntries = getMedications(date)
                    .map {
                        CalendarEntry(
                            id = it.id,
                            icon = MedicationUI.icon,
                            title = it.name,
                            time = it.takeAt,
                            colour = MedicationUI.colour,
                            type = CalendarFeatureTypes.MEDICATION
                        )
                    }

                addAll(medicationEntries)
            }

            if (CalendarFeatureTypes.REMINDERS in featureTypes) {
                val reminderEntries = getReminders(date)
                    .map {
                        CalendarEntry(
                            id = it.id,
                            icon = RemindersUI.icon,
                            title = it.message,
                            time = it.time,
                            colour = RemindersUI.colour,
                            type = CalendarFeatureTypes.REMINDERS
                        )
                    }

                addAll(reminderEntries)
            }
        }.sortedBy { it.time }

        _state.update { it.copy(
            calendarEntries = calendarEntries
        ) }

        applyFilter()
    }

    fun getFeaturetypes() {
        val calendarFeatureTypes = buildList {
            if (permissionsManager.hasAccess(FeatureType.DIARY)) {
                add(CalendarFeatureTypes.DIARY)
            }
            if (permissionsManager.hasAccess(FeatureType.MEDICATION)) {
                add(CalendarFeatureTypes.MEDICATION)
            }
            if (permissionsManager.hasAccess(FeatureType.REMINDERS)) {
                add(CalendarFeatureTypes.REMINDERS)
            }
        }

        _state.update { it.copy(
            calendarFeatureTypes = calendarFeatureTypes
        ) }
    }

    fun toggleFilterMenu() {
        _state.update { it.copy(
            showFilterMenu = !state.value.showFilterMenu
        ) }
    }

    fun toggleFilterOptions(appliedFilters: List<CalendarFeatureTypes>) {
        _state.update { it.copy(appliedFilters = appliedFilters) }

        applyFilter()
    }

    private fun applyFilter() {
        val appliedFilters = _state.value.appliedFilters

        val filtered = state.value.calendarEntries.filter { entry ->
            appliedFilters.isEmpty() || entry.type in appliedFilters
        }

        _state.update {
            it.copy(filteredCalendarEntries = filtered)
        }
    }

    fun getDiaryEntry(date: LocalDate): DiaryEntry? {
        return diaryRepository.getEntryForDate(date)
    }

    fun getMedications(date: LocalDate): List<Medication> {
        return medicationRepository.getMedicationsForDate(date)
    }

    fun getReminders(date: LocalDate): List<Reminder> {
        return remindersRepository.getRemindersForDate(date)
    }

    fun nextDay() = dateStateHolder.nextDay()
    fun previousDay() = dateStateHolder.previousDay()
}