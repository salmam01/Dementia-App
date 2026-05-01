package com.example.dementiaapp.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class UserPermissions(
    val featurePermissions: List<FeaturePermissions>,
    val settingsPermissions: List<SettingPermissions>
)

@Serializable
data class FeaturePermissions(
    val feature: Feature,
    val isEnabled: Boolean,
    val permittedActions: List<PermittedActions>
) {
    fun isActionPermitted(
        action: FeatureAction
    ): Boolean {
        if(!isEnabled) return false
        return permittedActions.find {
            it.action == action
        }?.isPermitted ?: false
    }
}

@Serializable
data class PermittedActions(
    val action: FeatureAction,
    val isPermitted: Boolean
)

@Serializable
data class SettingPermissions(
    val type: SettingType,
    val canAccess: Boolean
)