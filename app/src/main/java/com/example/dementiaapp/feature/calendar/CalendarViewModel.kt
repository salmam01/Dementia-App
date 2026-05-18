package com.example.dementiaapp.feature.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dementiaapp.domain.models.feature.DiaryEntry
import com.example.dementiaapp.domain.models.FeatureType
import com.example.dementiaapp.domain.models.feature.CalendarEntry
import com.example.dementiaapp.domain.models.feature.CalendarFeatureTypes
import com.example.dementiaapp.domain.models.feature.Medication
import com.example.dementiaapp.domain.models.feature.Reminder
import com.example.dementiaapp.domain.state.AppStateHolder
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
    private val appStateHolder: AppStateHolder,
    private val dateStateHolder: DateStateHolder,
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
        getFeatureTypes()
        observeSelectedDate()
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

    fun getFeatureTypes() {
        val permissionsPolicy = appStateHolder.appState.value.permissionsPolicy
        val calendarFeatureTypes = buildList {
            if (permissionsPolicy.isFeatureEnabled(FeatureType.DIARY)) {
                add(CalendarFeatureTypes.DIARY)
            }
            if (permissionsPolicy.isFeatureEnabled(FeatureType.MEDICATION)) {
                add(CalendarFeatureTypes.MEDICATION)
            }
            if (permissionsPolicy.isFeatureEnabled(FeatureType.REMINDERS)) {
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

    fun toggleFilterOptions(filtersToApply: List<CalendarFeatureTypes>) {
        _state.update { it.copy(appliedFilters = filtersToApply) }

        applyFilter()
    }

    private fun applyFilter() {
        val appliedFilters = _state.value.appliedFilters
        val calendarEntries = _state.value.calendarEntries

        // if no filters are applied, return the entire list, else filter the
        // list by matching the type to the applied filters
        val filtered = calendarEntries.filter { entry ->
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