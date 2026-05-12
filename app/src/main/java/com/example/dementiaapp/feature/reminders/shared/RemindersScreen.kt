package com.example.dementiaapp.feature.reminders.shared

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dementiaapp.domain.models.FeatureAction
import com.example.dementiaapp.domain.models.UserRole
import com.example.dementiaapp.feature.reminders.caregiver.CGRemindersScreen
import com.example.dementiaapp.feature.reminders.carerecipient.CRRemindersScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun RemindersScreen(
    onAddOrEditReminder: (String?) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: RemindersViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val user by viewModel.user.collectAsStateWithLifecycle()

    val canAdd = viewModel.hasAccessToAction(FeatureAction.ADD)
    val canEdit = canAdd
            && viewModel.hasAccessToAction(FeatureAction.EDIT)
    val canDelete = canAdd && canEdit
            && viewModel.hasAccessToAction(FeatureAction.DELETE)

    LaunchedEffect(Unit) {
        viewModel.refresh()
    }

    if (user?.role == UserRole.CARE_RECIPIENT) {
        CRRemindersScreen(
            viewModel,
            onAddOrEditReminder
        )
    } else {
        CGRemindersScreen(
            viewModel,
            onAddOrEditReminder
        )
    }
}