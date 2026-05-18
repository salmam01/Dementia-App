package com.example.dementiaapp.feature.myfamily.form

import androidx.lifecycle.ViewModel
import com.example.dementiaapp.domain.models.Person
import com.example.dementiaapp.repository.features.MyFamilyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate

class MyFamilyFormViewModel(
    private val myFamilyRepository: MyFamilyRepository
): ViewModel() {
    private val _state = MutableStateFlow(MyFamilyFormState())
    val state = _state.asStateFlow()

    fun getEntryById(id: String) {
        val result = myFamilyRepository.getEntryById(id)
        if (result != null) {
            setSelectedEntry(result)
        }
    }

    fun addOrEditEntry() {
        if (state.value.isValid) {
            if (state.value.isEditing) {
                myFamilyRepository.editEntry(state.value.draftEntry)
            } else {
                myFamilyRepository.addEntry(state.value.draftEntry)
                println("Added Reminder")
            }
        }
    }

    fun setSelectedEntry(entry: Person) {
        _state.update { it.copy(
            isEditing = true,
            selectedEntry = entry,
            draftEntry = entry
        ) }
    }

    fun setFullName(name: String) {
        _state.update {
            val newDraft = it.draftEntry.copy(fullName = name)

            it.copy(
                draftEntry = newDraft,
                isValid = validateEntry(newDraft)
            )
        }
    }

    fun setRelationShip(relationShip: String) {
        _state.update {
            val newDraft = it.draftEntry.copy(relationShip = relationShip)

            it.copy(
                draftEntry = newDraft,
                isValid = validateEntry(newDraft)
            )
        }
    }

    fun setNickName(name: String) {
        _state.update {
            val newDraft = it.draftEntry.copy(nickName = name)

            it.copy(
                draftEntry = newDraft,
                isValid = validateEntry(newDraft)
            )
        }
    }

    fun setBirthday(date: LocalDate) {
        _state.update {
            val newDraft = it.draftEntry.copy(birthday = date)

            it.copy(
                draftEntry = newDraft,
                isValid = validateEntry(newDraft)
            )
        }
    }

    fun setAddress(address: String) {
        _state.update {
            val newDraft = it.draftEntry.copy(address = address)

            it.copy(
                draftEntry = newDraft,
                isValid = validateEntry(newDraft)
            )
        }
    }

    fun setNumber(number: String) {
        _state.update {
            val newDraft = it.draftEntry.copy(number = number)

            it.copy(
                draftEntry = newDraft,
                isValid = validateEntry(newDraft)
            )
        }
    }

    fun setHobbies(hobbies: String) {
        _state.update {
            val newDraft = it.draftEntry.copy(hobbies = hobbies)

            it.copy(
                draftEntry = newDraft,
                isValid = validateEntry(newDraft)
            )
        }
    }

    fun setFavouriteFood(food: String) {
        _state.update {
            val newDraft = it.draftEntry.copy(favouriteFood = food)

            it.copy(
                draftEntry = newDraft,
                isValid = validateEntry(newDraft)
            )
        }
    }

    fun setFavouriteColour(colour: String) {
        _state.update {
            val newDraft = it.draftEntry.copy(favouriteColour = colour)

            it.copy(
                draftEntry = newDraft,
                isValid = validateEntry(newDraft)
            )
        }
    }

    fun setFavouriteBook(book: String) {
        _state.update {
            val newDraft = it.draftEntry.copy(favouriteBook = book)

            it.copy(
                draftEntry = newDraft,
                isValid = validateEntry(newDraft)
            )
        }
    }

    fun setFavouriteMusic(music: String) {
        _state.update {
            val newDraft = it.draftEntry.copy(favouriteMusic = music)

            it.copy(
                draftEntry = newDraft,
                isValid = validateEntry(newDraft)
            )
        }
    }

    private fun validateEntry(entry: Person): Boolean {
        return entry.fullName.isNotBlank() &&
                entry.relationShip.isNotBlank() &&
                entry.nickName.isNullOrBlank()
    }

    fun clearState() {
        _state.update { it.copy(
            isEditing = false,
            isValid = false,
            selectedEntry = null,
            draftEntry = Person(
                id = "",
                fullName = "",
                relationShip = "",
                image = "",
                nickName = "",
                birthday = null,
                address = "",
                number = "",
                hobbies = "",
                favouriteColour = "",
                favouriteBook = "",
                favouriteFood = "",
                favouriteMusic = ""
            )
        ) }
    }
}