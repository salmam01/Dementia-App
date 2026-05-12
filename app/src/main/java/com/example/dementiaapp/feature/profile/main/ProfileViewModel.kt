package com.example.dementiaapp.feature.profile.main

import androidx.lifecycle.ViewModel
import com.example.dementiaapp.domain.configuration.PermissionsManager
import com.example.dementiaapp.domain.models.FeatureAction
import com.example.dementiaapp.domain.models.FeatureType
import com.example.dementiaapp.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel(
    private val userRepository: UserRepository,
    private val permissionsManager: PermissionsManager
): ViewModel() {
    private val _state = MutableStateFlow(initializeState())
    val state = _state.asStateFlow()

    fun initializeState(): ProfileState {
        // for demonstration purposes
        val carePartnerId = "1"

        userRepository.assignCarePartner(carePartnerId)

        val currentUser = userRepository.getCurrentUser()
        val carePartner = userRepository.getCarePartner(carePartnerId)

        return ProfileState(
            currentUser,
            carePartner,
            profileNavigation = listOf(
                ProfileNavigation.MY_DATA,
                ProfileNavigation.SETTINGS,
                ProfileNavigation.CARE_PARTNER
            )
        )
    }

    fun hasAccessToAction(
        action: FeatureAction
    ): Boolean {
        return permissionsManager.hasPermission(
            action = action,
            featureType = FeatureType.PROFILE
        )
    }
}