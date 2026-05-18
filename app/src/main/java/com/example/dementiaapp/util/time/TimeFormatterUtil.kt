package com.example.dementiaapp.util.time
import com.example.dementiaapp.domain.models.DayTime
import com.example.dementiaapp.domain.models.TimeAdverb
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object TimeFormatterUtil {
    fun formatDateTimeToMonthDay(dateTime: LocalDateTime): String {
        return dateTime.format(
            DateTimeFormatter.ofPattern("MMMM d")
        )
    }

    fun formatDateToMonthDay(date: LocalDate): String {
        return date.format(
            DateTimeFormatter.ofPattern("MMMM d")
        )
    }

    fun formatBirthday(date: LocalDate): String {
        return date.format(
            DateTimeFormatter.ofPattern("dd.MM.yyyy")
        )
    }

    fun formatTime(dateTime: LocalDateTime): String {
        return dateTime.format(
            DateTimeFormatter.ofPattern("HH:mm")
        )
    }

    fun formatLocalTime(time: LocalTime): String {
        return time.format(
            DateTimeFormatter.ofPattern("HH:mm")
        )
    }

    fun formatMonth(dateTime: LocalDateTime): String {
        return dateTime.month.name.lowercase()
            .replaceFirstChar { it.uppercase() }
    }

    fun formatDay(dateTime: LocalDateTime): String {
        return dateTime.dayOfWeek.name.lowercase()
            .replaceFirstChar { it.uppercase() }
    }

    fun formatDayTime(dayTime: DayTime): String {
        return when (dayTime) {
            DayTime.MORNING -> "Morning"
            DayTime.AFTERNOON -> "Afternoon"
            DayTime.EVENING -> "Evening"
            DayTime.NIGHT -> "Night"
        }
    }

    fun formatTimeAdverb(timeAdverb: TimeAdverb): String {
        return when (timeAdverb) {
            TimeAdverb.TODAY -> "Today"
            TimeAdverb.YESTERDAY -> "Yesterday"
            TimeAdverb.TOMORROW -> "Tomorrow"
        }
    }
}