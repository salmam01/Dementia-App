package com.example.dementiaapp.domain.time

import com.example.dementiaapp.domain.models.DayTime
import com.example.dementiaapp.domain.models.TimeAdverb
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

object TimeProvider {
    fun LocalDateTime.toDayTime(): DayTime {
        return when (hour) {
            in 5..11 -> DayTime.MORNING
            in 12..16 -> DayTime.AFTERNOON
            in 17..21 -> DayTime.EVENING
            else -> DayTime.NIGHT
        }
    }

    fun LocalTime.toDayTime(): DayTime {
        return when (hour) {
            in 5..11 -> DayTime.MORNING
            in 12..16 -> DayTime.AFTERNOON
            in 17..21 -> DayTime.EVENING
            else -> DayTime.NIGHT
        }
    }

    fun LocalDate.toTimeAdverb(): TimeAdverb? {
        val today = LocalDate.now()

        return when (this) {
            today -> TimeAdverb.TODAY
            today.minusDays(1) -> TimeAdverb.YESTERDAY
            today.plusDays(1) -> TimeAdverb.TOMORROW
            else -> null
        }
    }
}