package com.example.dementiaapp.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dementiaapp.domain.configuration.AllFeatures
import com.example.dementiaapp.domain.configuration.PermissionsManager
import com.example.dementiaapp.domain.models.Feature
import com.example.dementiaapp.domain.models.FeatureType
import com.example.dementiaapp.features.FeatureItem
import com.example.dementiaapp.features.mapFeaturesToFeaturesItems
import com.example.dementiaapp.features.toFeatureUI
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
    private val userRepository: UserRepository,
    private val permissionsManager: PermissionsManager
): ViewModel() {
    private val _state = MutableStateFlow(HomeState(
        user = userRepository.getCurrentUser(),
        features = mapFeaturesToFeaturesItems(
            features = permissionsManager.getAllFeaturesWithAccess()
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
}