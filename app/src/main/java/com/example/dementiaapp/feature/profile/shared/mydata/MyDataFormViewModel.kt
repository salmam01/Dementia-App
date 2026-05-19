package com.example.dementiaapp.feature.profile.shared.mydata

import androidx.lifecycle.ViewModel
import com.example.dementiaapp.domain.models.User
import com.example.dementiaapp.domain.models.UserGender
import com.example.dementiaapp.domain.models.UserRole
import com.example.dementiaapp.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate

class MyDataFormViewModel(
    private val userRepository: UserRepository
): ViewModel() {
    private val _state = MutableStateFlow(MyDataFormState())
    val state = _state.asStateFlow()

    fun getUser() {
        _state.update { it.copy(
            draftUser = userRepository.getCurrentUser()
        ) }
    }

    fun updateUser() {
        val user = state.value.draftUser
        println(user)
        if (state.value.isValid) {
            userRepository.updateUser(user)
        }
    }

    fun setName(name: String) {
        _state.update {
            val newDraft = it.draftUser.copy(name = name)

            it.copy(
                draftUser = newDraft,
                isValid = validateUserData(newDraft)
            )
        }
    }

    fun setBirthday(date: LocalDate) {
        _state.update {
            val newDraft = it.draftUser.copy(birthday = date)

            it.copy(
                draftUser = newDraft,
                isValid = validateUserData(newDraft)
            )
        }
    }

    fun setGender(gender: UserGender) {
        _state.update {
            val newDraft = it.draftUser.copy(gender = gender)

            it.copy(
                draftUser = newDraft,
                isValid = validateUserData(newDraft)
            )
        }
    }

    fun setAddress(address: String) {
        _state.update {
            val newDraft = it.draftUser.copy(address = address)

            it.copy(
                draftUser = newDraft,
                isValid = validateUserData(newDraft)
            )
        }
    }

    fun setNumber(number: String) {
        _state.update {
            val newDraft = it.draftUser.copy(number = number)

            it.copy(
                draftUser = newDraft,
                isValid = validateUserData(newDraft)
            )
        }
    }

    fun setOrigin(origin: String) {
        _state.update {
            val newDraft = it.draftUser.copy(origin = origin)

            it.copy(
                draftUser = newDraft,
                isValid = validateUserData(newDraft)
            )
        }
    }

    fun setBirthPlace(birthPlace: String) {
        _state.update {
            val newDraft = it.draftUser.copy(birthPlace = birthPlace)

            it.copy(
                draftUser = newDraft,
                isValid = validateUserData(newDraft)
            )
        }
    }

    private fun validateUserData(user: User): Boolean {
        return user.name.isNotBlank() &&
                user.address.isNotBlank() &&
                user.address.isNotBlank() &&
                user.number.isNotBlank() &&
                user.origin.isNotBlank() &&
                user.birthPlace.isNotBlank()
    }

    fun clearState() {
        _state.update { it.copy(
            isEditing = true,
            draftUser = User(
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
            isValid = true
        ) }
    }
}