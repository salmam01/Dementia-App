package com.example.dementiaapp.domain.configuration

import com.example.dementiaapp.domain.models.Feature
import com.example.dementiaapp.domain.models.FeatureAction
import com.example.dementiaapp.domain.models.FeatureType

object AllFeatures {
    val Calendar = Feature(
        type = FeatureType.CALENDAR,
        actions = listOf(
            FeatureAction.FILTER,
        )
    )

    val Diary = Feature(
        type = FeatureType.DIARY,
        actions = listOf(
            FeatureAction.ADD,
            FeatureAction.EDIT,
            FeatureAction.DELETE
        )
    )

    val MyFamily = Feature(
        type = FeatureType.MY_FAMILY,
        actions = listOf(
            FeatureAction.ADD,
            FeatureAction.EDIT,
            FeatureAction.DELETE
        )
    )

    val Medication = Feature(
        type = FeatureType.MEDICATION,
        actions = listOf(
            FeatureAction.ADD,
            FeatureAction.EDIT,
            FeatureAction.DELETE
        )
    )

    val Reminders = Feature(
        type = FeatureType.REMINDERS,
        actions = listOf(
            FeatureAction.ADD,
            FeatureAction.EDIT,
            FeatureAction.DELETE
        )
    )

    val Logs = Feature(
        type = FeatureType.LOGS,
        actions = listOf(
            FeatureAction.FILTER
        )
    )

    val Call = Feature(
        type = FeatureType.CALL,
        actions = emptyList()
    )

    val Profile = Feature(
        type = FeatureType.PROFILE,
        actions = listOf(
            FeatureAction.EDIT
        )
    )
}
