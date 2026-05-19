package com.example.dementiaapp.repository

import com.example.dementiaapp.domain.models.User
import com.example.dementiaapp.domain.models.UserGender
import com.example.dementiaapp.domain.models.UserRole
import java.time.LocalDate

interface UserRepository {
    fun getCurrentUser(): User
    fun assignCarePartner(carePartnerId: String)
    fun getCarePartner(carePartnerId: String): User?
    fun updateUser(user: User)
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

    private val currentUserId = "2"

    override fun getCurrentUser(): User {
        return users.first { it.id == currentUserId}
    }

    override fun assignCarePartner(
        carePartnerId: String
    ) {
        getCurrentUser().carePartnerId = carePartnerId
    }

    override fun getCarePartner(
        carePartnerId: String
    ): User? {
        return users.firstOrNull {
            it.id == carePartnerId
        }
    }

    override fun updateUser(user: User) {
        val index = users.indexOfFirst { it.id == user.id }
        if (index != -1) {
            users[index] = user
        }
        println(users)
    }
}