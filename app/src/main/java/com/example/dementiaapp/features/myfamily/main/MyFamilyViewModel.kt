package com.example.dementiaapp.features.myfamily.main

import androidx.lifecycle.ViewModel
import com.example.dementiaapp.domain.configuration.PermissionsManager
import com.example.dementiaapp.domain.models.FeatureAction
import com.example.dementiaapp.domain.models.FeatureType
import com.example.dementiaapp.domain.models.Person
import com.example.dementiaapp.repository.features.MyFamilyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MyFamilyViewModel(
    private val myFamilyRepository: MyFamilyRepository,
    private val permissionsManager: PermissionsManager
): ViewModel() {
    private val _state = MutableStateFlow(MyFamilyState())
    val state = _state.asStateFlow()

    fun getEntries() {
        val result = myFamilyRepository.getMyFamilyEntries()
        _state.update { it.copy(entries = result) }
    }

    fun hasAccessToAction(
        action: FeatureAction
    ): Boolean {
        return permissionsManager.hasPermission(
            action = action,
            featureType = FeatureType.MY_FAMILY
        )
    }

    fun deleteEntry(entry: Person) {
        myFamilyRepository.deleteEntry(entry)
        _state.update { it.copy(
            selectedEntry = null,
            showConfirmationDialog = false
        ) }
        getEntries()
    }

    fun setSelectedEntry(entry: Person) {
        _state.update { it.copy(selectedEntry = entry) }
    }

    fun showConfirmationDialog() {
        _state.update { it.copy(
            showConfirmationDialog = true
        ) }
    }

    fun hideConfirmationDialog() {
        _state.update { it.copy(
            showConfirmationDialog = false
        ) }
    }
}