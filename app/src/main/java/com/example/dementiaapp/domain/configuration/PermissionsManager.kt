package com.example.dementiaapp.domain.configuration

import com.example.dementiaapp.domain.models.Feature
import com.example.dementiaapp.domain.models.FeatureAction
import com.example.dementiaapp.domain.models.FeatureType
import com.example.dementiaapp.domain.models.SettingType
import com.example.dementiaapp.domain.models.User
import com.example.dementiaapp.domain.models.UserRole
import com.example.dementiaapp.domain.state.UserStateHolder
import com.example.dementiaapp.repository.UserRepository

// Feature visibility, action authorization, settings access
class PermissionsManager {
    fun createPermissionsPolicy(user: User): PermissionsPolicy {
        return if (user.role == UserRole.CAREGIVER)
            PermissionsPolicy(userPermissions = DefaultPermissions.defaultCaregiverPermissions)
        else
            PermissionsPolicy(userPermissions = DefaultPermissions.defaultCareRecipientPermissions)
    }

    fun getAllFeaturesWithAccess(user: User): List<Feature> {
        return if (user.role == UserRole.CAREGIVER) {
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
}
