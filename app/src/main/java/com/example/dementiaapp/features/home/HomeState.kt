package com.example.dementiaapp.features.home

import com.example.dementiaapp.domain.models.User
import com.example.dementiaapp.features.FeatureItem

data class HomeState(
    val user: User,
    val features: List<FeatureItem>,
    val currentDate: String ?= null,
    val currentDay: String ?= null,
    val currentTime: String ?= null
)