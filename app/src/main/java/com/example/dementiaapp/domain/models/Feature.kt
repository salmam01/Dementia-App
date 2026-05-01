package com.example.dementiaapp.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Feature(
    val type: FeatureType,
    val actions: List<FeatureAction>?
)

enum class FeatureType {
    CALENDAR,
    DIARY,
    MY_FAMILY,
    MEDICATION,
    REMINDERS,
    CALL,
    LOGS,
    PROFILE
}

enum class FeatureAction {
    ADD,
    EDIT,
    DELETE,
    FILTER
}

fun Feature.createDefaultActionPermission(
    defaultValue: Boolean
): List<PermittedActions> {
    return actions?.map { action ->
        PermittedActions(
            action = action,
            isPermitted = defaultValue
        )
    } ?: emptyList()
}