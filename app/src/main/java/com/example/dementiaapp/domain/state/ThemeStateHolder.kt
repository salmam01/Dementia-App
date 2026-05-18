package com.example.dementiaapp.domain.state

import com.example.dementiaapp.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/*
class ThemeStateHolder(
    private val settingsRepository: SettingsRepository
) {
    private val _theme = MutableStateFlow<AppTheme>(
        settingsRepository.getSettings()
    )
    val theme = _theme.asStateFlow()
}
 */