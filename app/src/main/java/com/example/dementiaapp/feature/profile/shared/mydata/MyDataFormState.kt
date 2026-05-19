package com.example.dementiaapp.feature.profile.shared.mydata

import com.example.dementiaapp.domain.models.User
import com.example.dementiaapp.domain.models.UserGender
import com.example.dementiaapp.domain.models.UserRole
import java.time.LocalDate

data class MyDataFormState(
    val isEditing: Boolean = true,
    val draftUser: User = User(
        id = "",
        name = "",
        role = UserRole.CAREGIVER,
        carePartnerId = null,
        image = null,
        gender = UserGender.FEMALE,
        birthday = LocalDate.now(),
        address = "",
        number = "",
        origin = "",
        birthPlace = ""
    ),
    val isValid: Boolean = true
)
