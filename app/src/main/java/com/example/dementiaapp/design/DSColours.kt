package com.example.dementiaapp.design

import androidx.compose.ui.graphics.Color

object DSColours {
    val Primary = Color(0xFF245B51)
    val OnPrimary = Color(0xFFFFFFFF)

    val Secondary = Color(0xFF0C241F)
    val OnSecondary = Color(0xFFFFFFFF)

    val Tertiary = Color(0xFFBBEFEF)
    val OnTertiary = Color(0xFF000000)

    val Surface = Color(0xFFFFFFFF)
    val SurfaceVariant = Color(0xFFE6E6E6)
    val OnSurface = Color(0xFF000000)

    val Element = Color(0xFFD9D9D9)
    val Accent = Color(0xFFC5C5C5)

    val Divider = Color(0xFF9C9CA3)

    val DisabledContainer = Color(0xFF1E1E1E).copy(alpha = 0.12f)
    val OnDisabled =  Color(0xFF1E1E1E).copy(alpha = 0.38f)

    val CheckBoxOutline = Color(0xFFCECECE)
    val CheckBoxBackground = Color(0xFF787880)

    val PositiveActionPrimary = Color(0xFF006624)
    val PositiveActionContainer = Color(0xFFBFEDCF)
    val PositiveActionOutline = Color(0xFF00481A)

    val NegativeActionPrimary = Color(0xFFB60000)
    val NegativeActionContainer = Color(0xFFEEBCBC)
    val NegativeActionOutline = Color(0xFF7D0000)

    val EditPrimary = Color(0xFF585861)

    val Notification = Color(0xFFF5B85F)
    val Warning = Color(0xFFEA940A)

    val DayTime = Color(0xFFFFB820)
    val NightTime = Color(0xFFFFB820)

    object FeatureColours {
        object Calendar {
            val Primary = Color(0xFF623EC3)
        }

        object Diary {
            val Primary = Color(0xFF008939)
            val Accent = Color(0xFFB1EFA1)
        }

        object MyFamily {
            val Primary = Color(0xFFD8006F)
            val Container = Color(0xFFF6C2DB)
            val Outline = Color(0xFFE995BE)
        }

        object Medication {
            val Primary = Color(0xFF1A78E3)
            val Accent = Color(0xFFD4EDFF)
        }

        object Reminders {
            val Primary = Color(0xFF9B08B8)
            val Container = Color(0xFFF1DEF5)
        }
        object Call {
            val Primary = Color(0xFFB60000)
            val Container = Color(0xFFF4D9D9)
        }
    }
}