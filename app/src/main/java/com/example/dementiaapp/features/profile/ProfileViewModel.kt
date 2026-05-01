package com.example.dementiaapp.features.profile

import androidx.lifecycle.ViewModel
import com.example.dementiaapp.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel(
    private val userRepository: UserRepository
): ViewModel() {
    private val _state = MutableStateFlow(ProfileState(
        userRepository.getCurrentUser()
    ))
    val state = _state.asStateFlow()
}