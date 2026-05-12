package com.example.dementiaapp.domain.configuration

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit

data class AppearanceConfig(
    val reminderContainer: Color,
    val reminderBorder: Color,
    val titleSize: TextUnit,
    val contentSize: TextUnit
)

