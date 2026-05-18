package com.example.dementiaapp.domain.state

import com.example.dementiaapp.domain.configuration.PermissionsManager
import com.example.dementiaapp.domain.configuration.PermissionsPolicy
import com.example.dementiaapp.domain.models.Feature
import com.example.dementiaapp.domain.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * All State Holders should internally handle all operations so that AppState can reflect their results
 */

class AppStateHolder(
    userStateHolder: UserStateHolder,
    //themeStateHolder: ThemeStateHolder,
    permissionsManager: PermissionsManager
) {
    private val _appState = MutableStateFlow(
        AppState(
            user = userStateHolder.currentUser.value,
            //theme = themeStateHolder.theme.value,
            features = permissionsManager.getAllFeaturesWithAccess(
                userStateHolder.currentUser.value
            ),
            permissionsPolicy = permissionsManager.createPermissionsPolicy(
                userStateHolder.currentUser.value
            )
        )
    )
    val appState = _appState.asStateFlow()

    init {
        userStateHolder.initializeState()
    }
}

data class AppState(
    val user: User,
    //val theme: AppTheme,
    val features: List<Feature>,
    val permissionsPolicy: PermissionsPolicy
)