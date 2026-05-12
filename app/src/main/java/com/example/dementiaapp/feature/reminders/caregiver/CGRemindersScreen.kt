package com.example.dementiaapp.feature.reminders.caregiver

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dementiaapp.domain.models.FeatureAction
import com.example.dementiaapp.feature.reminders.shared.RemindersViewModel

@Composable
fun CGRemindersScreen(
    viewModel: RemindersViewModel,
    onAddOrEditReminder: (String?) -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val canAdd = viewModel.hasAccessToAction(FeatureAction.ADD)
    val canEdit = canAdd
            && viewModel.hasAccessToAction(FeatureAction.EDIT)
    val canDelete = canAdd && canEdit
            && viewModel.hasAccessToAction(FeatureAction.DELETE)
}