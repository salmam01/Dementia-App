package com.example.dementiaapp.domain.models

import java.time.LocalDate

data class User(
    val id: String,
    val name: String,
    val role: UserRole,
    val image: String? = null,
    val gender: UserGender,
    val birthday: LocalDate,
    val address: String,
    val number: String,
    val origin: String,
    val birthPlace: String
)

enum class UserRole {
    CAREGIVER,
    CARE_RECIPIENT
}

enum class UserGender {
    MALE,
    FEMALE
}