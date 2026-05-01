package com.example.dementiaapp.repository.features

import com.example.dementiaapp.domain.models.Medication
import com.example.dementiaapp.domain.models.MedicationType
import com.example.dementiaapp.domain.models.RepetitionType
import java.time.LocalDate
import java.time.LocalTime
import java.time.temporal.ChronoUnit

interface MedicationRepository {
    fun getMedications(): List<Medication>
    fun getMedicationsForDate(date: LocalDate): List<Medication>
    fun getMedicationById(id: String): Medication?

    fun addMedication(medication: Medication)
    fun editMedication(medication: Medication)
    fun deleteMedication(medication: Medication)
}

class MedicationRepositoryImpl: MedicationRepository{
    override fun getMedications(
    ): List<Medication> {
        return medicationsList
    }

    override fun getMedicationsForDate(
        date: LocalDate
    ): List<Medication> {
        return medicationsList.filter { medication ->
            when (medication.repeat) {
                RepetitionType.NONE -> {
                    medication.startDate.isEqual(date)
                }
                RepetitionType.DAILY -> {
                    !date.isBefore(medication.startDate)
                }
                RepetitionType.WEEKLY -> {
                    date.dayOfWeek == medication.startDate.dayOfWeek
                }
                RepetitionType.EVERY_2_DAYS -> {
                    !date.isBefore(medication.startDate) &&
                    ChronoUnit.DAYS.between(medication.startDate, date) % 2L == 0L
                }
            }
        }
    }

    override fun getMedicationById(id: String): Medication? {
        return medicationsList.firstOrNull { it.id == id }
    }

    override fun addMedication(medication: Medication) {
        medicationsList.add(medication)
    }

    override fun editMedication(medication: Medication) {
        val index = medicationsList.indexOfFirst { it.id == medication.id }
        if (index != -1) {
            medicationsList[index] = medication
        }
    }

    override fun deleteMedication(medication: Medication) {
        medicationsList.removeAll { it.id == medication.id }
        println("Remaining IDs: ${medicationsList.map { it.id }}")
    }

    private val medicationsList = mutableListOf(
        Medication(
            id = "1",
            name = "Eye Drops",
            dose = "2x",
            type = MedicationType.Drops,
            takeAt = LocalTime.of(20,0),
            startDate = LocalDate.of(2026, 4, 26),
            repeat = RepetitionType.DAILY,
            notes = "Only for the left eye!",
            completed = true
        ),
        Medication(
            id = "2",
            name = "Donepezil",
            dose = "10mg",
            type = MedicationType.Pill,
            takeAt = LocalTime.of(13,0),
            startDate = LocalDate.of(2026, 4, 26),
            repeat = RepetitionType.NONE,
            completed = false
        ),
        Medication(
            id = "3",
            name = "Atorvastatin",
            dose = "20mg",
            type = MedicationType.Pill,
            takeAt = LocalTime.of(9,0),
            startDate = LocalDate.of(2026, 4, 27),
            repeat = RepetitionType.EVERY_2_DAYS,
            completed = true
        ),
        Medication(
            id = "4",
            name = "Example",
            dose = "50ml",
            type = MedicationType.Injection,
            takeAt = LocalTime.of(16,30),
            startDate = LocalDate.of(2026, 4, 20),
            repeat = RepetitionType.WEEKLY,
            completed = true
        )
    )

}