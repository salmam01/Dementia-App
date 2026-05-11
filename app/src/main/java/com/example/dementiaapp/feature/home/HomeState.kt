package com.example.dementiaapp.feature.home

import com.example.dementiaapp.domain.models.User
import com.example.dementiaapp.feature.FeatureItem

data class HomeState(
    val user: User,
    val features: List<FeatureItem>,
    val currentDate: String ?= null,
    val currentDay: String ?= null,
    val currentTime: String ?= null
)