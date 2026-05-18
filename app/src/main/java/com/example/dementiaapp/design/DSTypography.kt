package com.example.dementiaapp.design

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object DSTypography {
    object Display {
        val ExtraLarge = 56.sp
        val Large = 48.sp
        val Medium = 36.sp
        val Small = 32.sp
        val ExtraSmall = 28.sp
    }
    // section / page titles
    object Headline {
        val ExtraLarge = 32.sp
        val Large = 28.sp
        val Medium = 24.sp
        val Small = 22.sp
        val ExtraSmall = 20.sp
    }
    // regular readable content
    object Body {
        val ExtraLarge = 26.sp
        val Large = 24.sp
        val Medium = 22.sp
        val Small = 20.sp
        val ExtraSmall = 16.sp
    }
}

object DSTypographyNew {
    object Display {
        val ExtraLarge = TextStyle(
            fontSize = 56.sp,
            fontWeight = FontWeight.ExtraBold,
        )
        val Large = TextStyle(
            fontSize = 48.sp,
            fontWeight = FontWeight.ExtraBold,
        )
        val Medium = TextStyle(
            fontSize = 36.sp,
            fontWeight = FontWeight.ExtraBold,
        )
        val Small = TextStyle(
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
        )
        val ExtraSmall = TextStyle(
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
        )
    }

    // section / page titles
    object Headline {
        val ExtraLarge = TextStyle(
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
        )
        val Large = TextStyle(
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
        )
        val Medium = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
        val Small = TextStyle(
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
        )
        val ExtraSmall = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    }

    object Title {
        val ExtraLarge = TextStyle(
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
        )
        val Large = TextStyle(
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
        )
        val Medium = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
        val Small = TextStyle(
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
        )
        val ExtraSmall = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    }

    object Body {
        val ExtraLarge = TextStyle(
            fontSize = 26.sp,
            fontWeight = FontWeight.Normal,
        )
        val Large = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
        )
        val Medium = TextStyle(
            fontSize = 22.sp,
            fontWeight = FontWeight.Normal,
        )
        val Small = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
        )
        val ExtraSmall = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
        )
    }

    object Label {
        val ExtraLarge = TextStyle(
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
        )
        val Large = TextStyle(
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
        )
        val Medium = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
        val Small = TextStyle(
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
        )
        val ExtraSmall = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}