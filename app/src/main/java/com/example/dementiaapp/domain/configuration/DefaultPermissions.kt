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
                feature = AppFeatures.Calendar,
                isEnabled = true,
                permittedActions = AppFeatures.Calendar
                    .createDefaultActionPermission(true)
            ),
            FeaturePermissions(
                feature = AppFeatures.Diary,
                isEnabled = false,
                permittedActions = AppFeatures.Diary
                    .createDefaultActionPermission(false)
            ),
            FeaturePermissions(
                feature = AppFeatures.MyFamily,
                isEnabled = false,
                permittedActions = AppFeatures.MyFamily
                    .createDefaultActionPermission(false)
            ),
            FeaturePermissions(
                feature = AppFeatures.Medication,
                isEnabled = true,
                permittedActions = AppFeatures.Medication
                    .createDefaultActionPermission(true)
            ),
            FeaturePermissions(
                feature = AppFeatures.Reminders,
                isEnabled = true,
                permittedActions = AppFeatures.Reminders
                    .createDefaultActionPermission(true)
            ),
            FeaturePermissions(
                feature = AppFeatures.Call,
                isEnabled = true,
                permittedActions = AppFeatures.Call
                    .createDefaultActionPermission(true)
            ),
            FeaturePermissions(
                feature = AppFeatures.Logs,
                isEnabled = true,
                permittedActions = AppFeatures.Logs
                    .createDefaultActionPermission(true)
            ),
            FeaturePermissions(
                feature = AppFeatures.Profile,
                isEnabled = true,
                permittedActions = AppFeatures.Profile
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
                feature = AppFeatures.Calendar,
                isEnabled = true,
                permittedActions = AppFeatures.Calendar
                    .createDefaultActionPermission(true)
            ),
            FeaturePermissions(
                feature = AppFeatures.Diary,
                isEnabled = true,
                permittedActions = AppFeatures.Diary
                    .createDefaultActionPermission(true)
            ),
            FeaturePermissions(
                feature = AppFeatures.MyFamily,
                isEnabled = true,
                permittedActions = AppFeatures.MyFamily
                    .createDefaultActionPermission(true)
            ),
            FeaturePermissions(
                feature = AppFeatures.Medication,
                isEnabled = true,
                permittedActions = AppFeatures.Medication
                    .createDefaultActionPermission(false)
            ),
            FeaturePermissions(
                feature = AppFeatures.Reminders,
                isEnabled = true,
                permittedActions = AppFeatures.Reminders
                    .createDefaultActionPermission(true)
            ),
            FeaturePermissions(
                feature = AppFeatures.Call,
                isEnabled = true,
                permittedActions = AppFeatures.Call
                    .createDefaultActionPermission(true)
            ),
            FeaturePermissions(
                feature = AppFeatures.Logs,
                isEnabled = false,
                permittedActions = AppFeatures.Logs
                    .createDefaultActionPermission(false)
            ),
            FeaturePermissions(
                feature = AppFeatures.Profile,
                isEnabled = false,
                permittedActions = AppFeatures.Profile
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