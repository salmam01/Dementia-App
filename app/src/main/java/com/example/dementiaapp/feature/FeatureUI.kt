package com.example.dementiaapp.feature

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.LibraryBooks
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Medication
import androidx.compose.material.icons.rounded.NotificationsActive
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.domain.models.Feature
import com.example.dementiaapp.domain.models.FeatureType
import com.example.dementiaapp.localization.LocalizedStrings
import kotlin.collections.map

val strings = LocalizedStrings.current

data class FeatureUI(
    val name: String,
    val colour: Color,
    val icon: ImageVector
)

data class FeatureItem(
    val feature: Feature,
    val ui: FeatureUI
)

object AllFeatureUI {
    val CalendarUI = FeatureUI(
        name = strings.calendar,
        colour = DSColours.FeatureColours.Calendar.Primary,
        icon = Icons.Rounded.CalendarMonth
    )
    val DiaryUI = FeatureUI(
        name = strings.diary,
        colour = DSColours.FeatureColours.Diary.Primary,
        icon = Icons.Rounded.Book
    )
    val MyFamilyUI = FeatureUI(
        name = strings.myFamily,
        colour = DSColours.FeatureColours.MyFamily.Primary,
        icon = Icons.Rounded.Favorite
    )
    val MedicationUI = FeatureUI(
        name = strings.medication,
        colour = DSColours.FeatureColours.Medication.Primary,
        icon = Icons.Rounded.Medication
    )
    val RemindersUI = FeatureUI(
        name = strings.reminders,
        colour = DSColours.FeatureColours.Reminders.Primary,
        icon = Icons.Rounded.NotificationsActive
    )
    val CallUI = FeatureUI(
        name = strings.call,
        colour = DSColours.FeatureColours.Call.Primary,
        icon = Icons.Rounded.Phone
    )
    val LogsUI = FeatureUI(
        name = strings.logs,
        colour = DSColours.Primary,
        icon = Icons.AutoMirrored.Filled.LibraryBooks
    )
    val ProfileUI = FeatureUI(
        name = strings.profile,
        colour = DSColours.Primary,
        icon = Icons.Rounded.Person
    )
}

fun mapFeaturesToFeaturesItems(
    features: List<Feature>
): List<FeatureItem> {
    return features.map { feature ->
        FeatureItem(
            feature = feature,
            ui = feature.toFeatureUI()
        )
    }
}

fun Feature.toFeatureUI(): FeatureUI {
    return when (this.type) {
        FeatureType.CALENDAR -> AllFeatureUI.CalendarUI
        FeatureType.DIARY -> AllFeatureUI.DiaryUI
        FeatureType.MY_FAMILY -> AllFeatureUI.MyFamilyUI
        FeatureType.MEDICATION -> AllFeatureUI.MedicationUI
        FeatureType.REMINDERS -> AllFeatureUI.RemindersUI
        FeatureType.CALL -> AllFeatureUI.CallUI
        FeatureType.LOGS -> AllFeatureUI.LogsUI
        FeatureType.PROFILE -> AllFeatureUI.ProfileUI
    }
}