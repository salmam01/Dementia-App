package com.example.dementiaapp.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route: NavKey {

    @Serializable
    data object Home: Route, NavKey

    @Serializable
    data object Message: Route, NavKey

    @Serializable
    data object Profile: Route, NavKey

    @Serializable
    data object Calendar: Route, NavKey

    @Serializable
    data object Diary: Route, NavKey

    @Serializable
    data object MyFamily: Route, NavKey

    @Serializable
    data object Medication: Route, NavKey

    @Serializable
    data object Reminders: Route, NavKey

    @Serializable
    data object Logs: Route, NavKey

    @Serializable
    data object Call: Route, NavKey
}