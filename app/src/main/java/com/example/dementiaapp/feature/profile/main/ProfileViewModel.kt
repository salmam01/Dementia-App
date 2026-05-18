package com.example.dementiaapp.feature.profile.main

import androidx.lifecycle.ViewModel
import com.example.dementiaapp.domain.configuration.PermissionsManager
import com.example.dementiaapp.domain.models.FeatureAction
import com.example.dementiaapp.domain.models.FeatureType
import com.example.dementiaapp.domain.state.AppStateHolder
import com.example.dementiaapp.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel(
    private val appStateHolder: AppStateHolder,
    private val userRepository: UserRepository
): ViewModel() {
    private val _state = MutableStateFlow(initializeState())
    val state = _state.asStateFlow()

    fun initializeState(): ProfileState {
        val currentUser = userRepository.getCurrentUser()
        val carePartner =
            if (currentUser.carePartnerId != null)
                userRepository.getCarePartner(currentUser.carePartnerId!!)
            else
                null

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
        return appStateHolder.appState.value.permissionsPolicy.canPerform(
            action = action,
            featureType = FeatureType.PROFILE
        )
    }
}