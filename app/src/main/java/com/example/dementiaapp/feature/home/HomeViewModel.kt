package com.example.dementiaapp.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dementiaapp.domain.state.AppStateHolder
import com.example.dementiaapp.feature.mapFeaturesToFeaturesItems
import com.example.dementiaapp.repository.UserRepository
import com.example.dementiaapp.util.time.TimeFormatterUtil
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class HomeViewModel(
    appStateHolder: AppStateHolder,
    private val userRepository: UserRepository
): ViewModel() {
    private val _state = MutableStateFlow(HomeState(
        user = appStateHolder.appState.value.user,
        features = mapFeaturesToFeaturesItems(
            features = appStateHolder.appState.value.features
        )
    ))
    val state = _state.asStateFlow()

    init {
        startClock()
    }

    private fun startClock() {
        viewModelScope.launch {
            while(isActive) {
                getCurrentTimeAndDate()
                delay(1000L)
            }
        }
    }

    private fun getCurrentTimeAndDate() {
        val date = LocalDateTime.now()

        _state.update { it.copy(
            currentDate = TimeFormatterUtil.formatDateTimeToMonthDay(date),
            currentDay = TimeFormatterUtil.formatDay(date),
            currentTime = TimeFormatterUtil.formatTime(date)
        ) }
    }

    fun getCarePartnerName(): String {
        val carePartnerId = state.value.user.carePartnerId
        if (carePartnerId != null) {
            val carePartner = userRepository.getCarePartner(carePartnerId)
            return carePartner?.name?.substringBefore(" ")!!
        } else {
            return ""
        }
    }
}