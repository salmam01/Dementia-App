package com.example.dementiaapp.repository

import com.example.dementiaapp.domain.models.User
import com.example.dementiaapp.domain.models.UserGender
import com.example.dementiaapp.domain.models.UserRole
import java.sql.Date
import java.time.LocalDate
import java.util.Calendar

interface UserRepository {
    fun getCurrentUser(): User
}

class UserRepositoryImpl: UserRepository {
    override fun getCurrentUser(): User {
        return demoCareRecipient
    }

    private val demoCaregiver = User(
        id = "1",
        name = "Linda Baker",
        role = UserRole.CAREGIVER,
        image = null,
        gender = UserGender.FEMALE,
        birthday = LocalDate.of(1992, 6, 15),
        address = "Hauptstraße 47, Top 12, 4040 Linz, Austria",
        number = "+43 664 1234578",
        origin = "England",
        birthPlace = "Austria"
    )

    private val demoCareRecipient = User(
        id = "2",
        name = "James Baker",
        role = UserRole.CARE_RECIPIENT,
        image = null,
        gender = UserGender.MALE,
        birthday = LocalDate.of(1947, 1, 26),
        address = "Rosaliengasse 19, Top 8, 8020 Graz, Austria",
        number = "+43 664 87654321",
        origin = "England",
        birthPlace = "Austria"
    )
}