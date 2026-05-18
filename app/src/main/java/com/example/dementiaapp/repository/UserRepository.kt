package com.example.dementiaapp.repository

import com.example.dementiaapp.domain.models.User
import com.example.dementiaapp.domain.models.UserGender
import com.example.dementiaapp.domain.models.UserRole
import java.time.LocalDate

interface UserRepository {
    fun getCurrentUser(): User
    fun assignCarePartner(carePartnerId: String)
    fun getCarePartner(carePartnerId: String): User?
}

class UserRepositoryImpl: UserRepository {
    private val users = mutableListOf(
        User(
            id = "1",
            name = "Linda Baker",
            role = UserRole.CAREGIVER,
            carePartnerId = null,
            image = null,
            gender = UserGender.FEMALE,
            birthday = LocalDate.of(1992, 6, 15),
            address = "Hauptstraße 47, Top 12, 4040 Linz, Austria",
            number = "+43 664 1234578",
            origin = "England",
            birthPlace = "Austria"
        ),
        User(
            id = "2",
            name = "James Baker",
            role = UserRole.CARE_RECIPIENT,
            carePartnerId = null,
            image = null,
            gender = UserGender.MALE,
            birthday = LocalDate.of(1947, 1, 26),
            address = "Rosaliengasse 19, Top 8, 8020 Graz, Austria",
            number = "+43 664 87654321",
            origin = "England",
            birthPlace = "Austria"
        )
    )

    private val currentUser = users.first {
        it.role == UserRole.CARE_RECIPIENT
    }

    override fun getCurrentUser(): User {
        return currentUser
    }

    override fun assignCarePartner(
        carePartnerId: String
    ) {
        currentUser.carePartnerId = carePartnerId
    }

    override fun getCarePartner(
        carePartnerId: String
    ): User? {
        return users.firstOrNull {
            it.id == carePartnerId
        }
    }
}