package com.example.dementiaapp.domain.configuration

import com.example.dementiaapp.domain.models.FeatureAction
import com.example.dementiaapp.domain.models.FeatureType
import com.example.dementiaapp.domain.models.SettingType
import com.example.dementiaapp.domain.models.UserPermissions

class PermissionsPolicy(
    private val userPermissions: UserPermissions
) {
    fun isFeatureEnabled(
        featureType: FeatureType
    ): Boolean {
        return userPermissions.featurePermissions.find {
            it.feature.type == featureType
        }?.isEnabled ?: false
    }

    fun canPerform(
        featureType: FeatureType,
        action: FeatureAction
    ): Boolean {
        return userPermissions.featurePermissions.find {
            it.feature.type == featureType
        }?.isActionPermitted(action) ?: false
    }

    fun canAccessSettings(
        settingType: SettingType
    ): Boolean {
        return userPermissions.settingsPermissions.find {
            it.type == settingType
        }?.canAccess ?: false
    }
}