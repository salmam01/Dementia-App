package com.example.dementiaapp.domain.configuration

import com.example.dementiaapp.domain.models.FeaturePermissions
import com.example.dementiaapp.domain.models.SettingPermissions
import com.example.dementiaapp.domain.models.SettingType
import com.example.dementiaapp.domain.models.UserPermissions
import com.example.dementiaapp.domain.models.createDefaultActionPermission

object DefaultPermissions {
    val defaultCaregiverPermissions = UserPermissions(
        featurePermissions = listOf(
            FeaturePermissions(
                feature = AllFeatures.Calendar,
                isEnabled = true,
                permittedActions = AllFeatures.Calendar
                    .createDefaultActionPermission(true)
            ),
            FeaturePermissions(
                feature = AllFeatures.Diary,
                isEnabled = false,
                permittedActions = AllFeatures.Diary
                    .createDefaultActionPermission(false)
            ),
            FeaturePermissions(
                feature = AllFeatures.MyFamily,
                isEnabled = false,
                permittedActions = AllFeatures.MyFamily
                    .createDefaultActionPermission(false)
            ),
            FeaturePermissions(
                feature = AllFeatures.Medication,
                isEnabled = true,
                permittedActions = AllFeatures.Medication
                    .createDefaultActionPermission(true)
            ),
            FeaturePermissions(
                feature = AllFeatures.Reminders,
                isEnabled = true,
                permittedActions = AllFeatures.Reminders
                    .createDefaultActionPermission(true)
            ),
            FeaturePermissions(
                feature = AllFeatures.Call,
                isEnabled = true,
                permittedActions = AllFeatures.Call
                    .createDefaultActionPermission(true)
            ),
            FeaturePermissions(
                feature = AllFeatures.Logs,
                isEnabled = true,
                permittedActions = AllFeatures.Logs
                    .createDefaultActionPermission(true)
            ),
            FeaturePermissions(
                feature = AllFeatures.Profile,
                isEnabled = true,
                permittedActions = AllFeatures.Profile
                    .createDefaultActionPermission(true)
            )
        ),
        settingsPermissions = listOf(
            SettingPermissions(
                type = SettingType.APPEARANCE,
                canAccess = true
            ),
            SettingPermissions(
                type = SettingType.NOTIFICATIONS,
                canAccess = true
            ),
            SettingPermissions(
                type = SettingType.PERMISSIONS,
                canAccess = true
            )
        )
    )

    val defaultCareRecipientPermissions = UserPermissions(
        featurePermissions = listOf(
            FeaturePermissions(
                feature = AllFeatures.Calendar,
                isEnabled = true,
                permittedActions = AllFeatures.Calendar
                    .createDefaultActionPermission(true)
            ),
            FeaturePermissions(
                feature = AllFeatures.Diary,
                isEnabled = true,
                permittedActions = AllFeatures.Diary
                    .createDefaultActionPermission(true)
            ),
            FeaturePermissions(
                feature = AllFeatures.MyFamily,
                isEnabled = true,
                permittedActions = AllFeatures.MyFamily
                    .createDefaultActionPermission(true)
            ),
            FeaturePermissions(
                feature = AllFeatures.Medication,
                isEnabled = true,
                permittedActions = AllFeatures.Medication
                    .createDefaultActionPermission(false)
            ),
            FeaturePermissions(
                feature = AllFeatures.Reminders,
                isEnabled = true,
                permittedActions = AllFeatures.Reminders
                    .createDefaultActionPermission(true)
            ),
            FeaturePermissions(
                feature = AllFeatures.Call,
                isEnabled = true,
                permittedActions = AllFeatures.Call
                    .createDefaultActionPermission(true)
            ),
            FeaturePermissions(
                feature = AllFeatures.Logs,
                isEnabled = false,
                permittedActions = AllFeatures.Logs
                    .createDefaultActionPermission(false)
            ),
            FeaturePermissions(
                feature = AllFeatures.Profile,
                isEnabled = false,
                permittedActions = AllFeatures.Profile
                    .createDefaultActionPermission(false)
            )
        ),
        settingsPermissions = listOf(
            SettingPermissions(
                type = SettingType.APPEARANCE,
                canAccess = true
            ),
            SettingPermissions(
                type = SettingType.NOTIFICATIONS,
                canAccess = false
            ),
            SettingPermissions(
                type = SettingType.PERMISSIONS,
                canAccess = false
            )
        )
    )
}