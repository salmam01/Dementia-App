package com.example.dementiaapp.domain.state

import com.example.dementiaapp.domain.models.User
import com.example.dementiaapp.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserStateHolder(
    userRepository: UserRepository
) {
    private val _currentUser = MutableStateFlow<User>(
        userRepository.getCurrentUser()
    )
    val currentUser = _currentUser.asStateFlow()
}