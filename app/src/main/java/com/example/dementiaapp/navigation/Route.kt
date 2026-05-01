package com.example.dementiaapp.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route: NavKey {

    @Serializable
    data object Home: Route

    @Serializable
    data object Chat: Route

    @Serializable
    data object Profile: Route

    @Serializable
    data object Calendar: Route

    @Serializable
    data object Diary: Route


    @Serializable
    data object MyFamily: Route
    @Serializable
    data object MyFamilyDetails: Route
    @Serializable
    data class ManageMyFamily(
        val selectedEntryId: String?
    ): Route

    @Serializable
    data object Medication: Route
    @Serializable
    data object AllMedications: Route
    @Serializable
    data class ManageMedication(
        val selectedMedicationId: String?
    ): Route

    @Serializable
    data object Reminders: Route
    @Serializable
    data class ManageReminders(
        val selectedReminderId: String?
    ): Route

    @Serializable
    data object Logs: Route
}

fun NavKey.title(): String = when (this) {
    Route.Home -> "Home"
    Route.Chat -> "Chat"
    Route.Profile -> "Profile"
    Route.Calendar -> "Calendar"
    Route.Diary -> "Diary"
    Route.MyFamily -> "My Family"
    Route.MyFamilyDetails -> "My Family"
    is Route.ManageMyFamily -> "Manage Entry"
    Route.Medication -> "Medication"
    Route.AllMedications -> "All Medications"
    is Route.ManageMedication -> "Manage Medication"
    Route.Reminders -> "Reminders"
    is Route.ManageReminders -> "Manage Reminder"
    Route.Logs -> "Logs"
    else -> ""
}