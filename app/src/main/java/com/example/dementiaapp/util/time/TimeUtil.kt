package com.example.dementiaapp.util.time

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Nightlight
import androidx.compose.material.icons.rounded.WbSunny
import androidx.compose.material.icons.rounded.WbTwilight
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.domain.models.DayTime

data class DayTimeUI (
    val name: String,
    val icon: ImageVector,
    val colour: Color
)

fun getDayTimeUI(dayTime: DayTime): DayTimeUI {
    val name = TimeFormatterUtil.formatDayTime(dayTime)

    val icon = when (dayTime) {
        DayTime.AFTERNOON, DayTime.EVENING -> Icons.Rounded.WbTwilight
        DayTime.MORNING -> Icons.Rounded.WbSunny
        else -> Icons.Rounded.Nightlight
    }

    val colour = when (dayTime) {
        DayTime.MORNING, DayTime.AFTERNOON -> DSColours.DayTime
        else -> DSColours.NightTime
    }

    return DayTimeUI(name, icon, colour)
}