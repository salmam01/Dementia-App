package com.example.dementiaapp.feature.profile.shared

import com.example.dementiaapp.domain.models.User

data class ProfileState(
    val user: User,
    val carePartner: User?,
    val profileNavigation: List<ProfileNavigation> = emptyList()
)

enum class ProfileNavigation{
    MY_DATA,
    SETTINGS,
    CARE_PARTNER
}