package com.example.dementiaapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChatBubble
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val icon: ImageVector,
    val title: String
)

val TOP_LEVEL_DESTINATIONS = mapOf(
    Route.Home to BottomNavItem(
        icon = Icons.Rounded.Home,
        title = "Home"
    ),
    Route.Chat to BottomNavItem(
        icon = Icons.Rounded.ChatBubble,
        title = "Chat"
    ),
    Route.Profile to BottomNavItem(
        icon = Icons.Rounded.Person,
        title = "Profile"
    ),
)