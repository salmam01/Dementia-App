package com.example.dementiaapp.features.myfamily.main

import com.example.dementiaapp.domain.models.Person

data class MyFamilyState(
    val entries: List<Person> ?= emptyList(),
    val selectedEntry: Person ?= null,
    val showConfirmationDialog: Boolean = false
)