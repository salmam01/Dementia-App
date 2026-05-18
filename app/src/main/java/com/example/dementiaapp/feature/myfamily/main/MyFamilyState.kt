package com.example.dementiaapp.feature.myfamily.main

import com.example.dementiaapp.domain.models.feature.Person

data class MyFamilyState(
    val entries: List<Person> ?= emptyList(),
    val selectedEntry: Person ?= null,
    val showConfirmationDialog: Boolean = false
)