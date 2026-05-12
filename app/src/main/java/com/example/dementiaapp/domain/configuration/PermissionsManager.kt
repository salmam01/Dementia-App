package com.example.dementiaapp.domain.configuration

import com.example.dementiaapp.domain.models.Feature
import com.example.dementiaapp.domain.models.FeatureAction
import com.example.dementiaapp.domain.models.FeatureType
import com.example.dementiaapp.domain.models.SettingType
import com.example.dementiaapp.domain.models.UserRole
import com.example.dementiaapp.domain.state.UserStateHolder
import com.example.dementiaapp.repository.UserRepository

// Feature visibility, action authorization, settings access
class PermissionsManager(
    private val userRepository: UserRepository,
    private val userStateHolder: UserStateHolder,
    //private val settingsRepository: SettingsRepository
) {
    // Temporarily use default permissions, switch to SettingsRepository with Proto DataStore later
    private val currentUser = userStateHolder.currentUser.value

    private val permissionsPolicy: PermissionsPolicy =
        if (currentUser?.role == UserRole.CAREGIVER)
            PermissionsPolicy(userPermissions = DefaultPermissions.defaultCaregiverPermissions)
        else
            PermissionsPolicy(userPermissions = DefaultPermissions.defaultCareRecipientPermissions)

    fun getAllFeaturesWithAccess(): List<Feature> {
        return if (currentUser?.role == UserRole.CAREGIVER) {
            DefaultPermissions.defaultCaregiverPermissions
                .featurePermissions
                .filter { it.isEnabled }
                .map { it.feature }
        }
        else {
            DefaultPermissions.defaultCareRecipientPermissions
                .featurePermissions
                .filter { it.isEnabled }
                .map { it.feature }
        }
    }

    fun hasAccess(featureType: FeatureType): Boolean {
        return permissionsPolicy.isFeatureEnabled(featureType)
    }

    fun hasPermission(
        featureType: FeatureType,
        action: FeatureAction
    ): Boolean {
        return permissionsPolicy.canPerform(featureType, action)
    }

    fun canAccessSettings(
        settingType: SettingType
    ): Boolean {
        return permissionsPolicy.canAccessSettings(settingType)
    }
}
