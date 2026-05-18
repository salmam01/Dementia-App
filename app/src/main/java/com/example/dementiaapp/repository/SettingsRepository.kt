package com.example.dementiaapp.repository


interface SettingsRepository {
    fun getSettings(userId: String): AppSettings
}

data class AppSettings(
    val idk: String
)