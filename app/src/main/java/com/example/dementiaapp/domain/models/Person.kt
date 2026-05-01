package com.example.dementiaapp.domain.models

import java.time.LocalDate

data class Person(
    val id: String,
    val fullName: String,
    val relationShip: String,
    val image: String? = null,
    val nickName: String? = null,
    val birthday: LocalDate? = null,
    val address: String? = null,
    val number: String? = null,
    val favouriteColour: String? = null,
    val favouriteFood: String? = null,
    val favouriteMusic: String? = null,
    val favouriteBook: String? = null,
    val hobbies: String? = null
)