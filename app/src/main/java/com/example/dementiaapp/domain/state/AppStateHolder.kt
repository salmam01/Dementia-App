package com.example.dementiaapp.domain.state

import com.example.dementiaapp.domain.configuration.PermissionsManager
import com.example.dementiaapp.domain.configuration.PermissionsPolicy
import com.example.dementiaapp.domain.models.Feature
import com.example.dementiaapp.domain.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

/**
 * All State Holders should internally handle all operations so that AppState can reflect their results
 */

class AppStateHolder(
    private val userStateHolder: UserStateHolder,
    //themeStateHolder: ThemeStateHolder,
    private val permissionsManager: PermissionsManager
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
        observeUserState()
    }

    private fun observeUserState() {
        userStateHolder.currentUser
            .onEach { user ->
                _appState.update {
                    it.copy(
                        user = user,
                        features = permissionsManager.getAllFeaturesWithAccess(user),
                        permissionsPolicy = permissionsManager.createPermissionsPolicy(user)
                    )
                }
            }
            .launchIn(CoroutineScope(SupervisorJob() + Dispatchers.Main))
    }
}

data class AppState(
    val user: User,
    //val theme: AppTheme,
    val features: List<Feature>,
    val permissionsPolicy: PermissionsPolicy
)