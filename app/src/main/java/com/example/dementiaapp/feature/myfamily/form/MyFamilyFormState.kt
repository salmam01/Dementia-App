package com.example.dementiaapp.feature.myfamily.form

import com.example.dementiaapp.domain.models.Person

data class MyFamilyFormState(
    val isEditing: Boolean = false,
    val isValid: Boolean = false,
    val selectedEntry: Person ?= null,
    val draftEntry: Person = Person(
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
)
