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
    data object CarePartner: Route

    @Serializable
    data object MyData: Route

    @Serializable
    data object Calendar: Route

    @Serializable
    data object Diary: Route
    @Serializable
    data class DiaryForm(
        val entryId: String?
    ): Route

    @Serializable
    data object MyFamily: Route
    @Serializable
    data object MyFamilyDetails: Route
    @Serializable
    data class MyFamilyForm(
        val selectedEntryId: String?
    ): Route

    @Serializable
    data object Medication: Route
    @Serializable
    data object AllMedications: Route
    @Serializable
    data class MedicationForm(
        val selectedMedicationId: String?
    ): Route

    @Serializable
    data object Reminders: Route
    @Serializable
    data class RemindersForm(
        val selectedReminderId: String?
    ): Route

    @Serializable
    data object Logs: Route

    @Serializable
    data object Call: Route
}

fun NavKey.title(): String = when (this) {
    Route.Home -> "Home"
    Route.Chat -> "Chat"
    Route.Profile -> "Profile"
    Route.CarePartner -> "My Care Partner"
    Route.MyData -> "My Information"
    Route.Calendar -> "Calendar"
    Route.Diary -> "Diary"
    is Route.DiaryForm -> "Diary"
    Route.MyFamily -> "My Family"
    Route.MyFamilyDetails -> "My Family"
    is Route.MyFamilyForm -> "My Family"
    Route.Medication -> "Medication"
    Route.AllMedications -> "All Medications"
    is Route.MedicationForm -> "Medication"
    Route.Reminders -> "Reminders"
    is Route.RemindersForm -> "Reminders"
    Route.Logs -> "Logs"
    Route.Call -> "Call"
    else -> ""
}