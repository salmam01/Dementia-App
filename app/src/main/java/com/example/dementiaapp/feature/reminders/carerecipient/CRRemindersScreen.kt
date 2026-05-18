package com.example.dementiaapp.feature.reminders.carerecipient

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.design.DSTypography
import com.example.dementiaapp.domain.models.FeatureAction
import com.example.dementiaapp.domain.models.Reminder
import com.example.dementiaapp.feature.components.CheckBox
import com.example.dementiaapp.feature.components.ConfirmationDialogue
import com.example.dementiaapp.feature.components.time.DatePickerSection
import com.example.dementiaapp.feature.components.buttons.ActionButton
import com.example.dementiaapp.feature.components.buttons.ActionButtonStyles
import com.example.dementiaapp.feature.components.buttons.StickyActionButton
import com.example.dementiaapp.feature.reminders.shared.RemindersViewModel
import com.example.dementiaapp.util.time.TimeFormatterUtil

@Composable
fun CRRemindersScreen(
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

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = DSDimensions.AddButtonSpace)
        ) {
            DatePickerSection(
                date = state.selectedDate,
                onNextClick = { viewModel.nextDay() },
                onPreviousClick = { viewModel.previousDay() }
            )
            Spacer(modifier = Modifier.height(DSDimensions.Space4))

            RemindersList(
                state.reminders,
                canEdit = canEdit,
                canDelete = canDelete,
                onItemCompleted = { item ->
                    viewModel.setItemCompleted(item = item)
                },
                onEditClick = { onAddOrEditReminder(it.id) },
                onDeleteClick = {
                    viewModel.setSelectedReminder(it)
                    viewModel.showConfirmationDialog()
                }
            )
        }

        if (canAdd) {
            StickyActionButton(
                text = "Add Reminder",
                colour = DSColours.FeatureColours.Reminders.Primary,
                onClick = { onAddOrEditReminder(null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )
        }

        if (state.showConfirmationDialog) {
            val selectedReminder = state.selectedReminder
            if (selectedReminder != null) {
                ConfirmationDialogue(
                    text = "delete the entry \"${selectedReminder.message}\"",
                    onConfirm = { viewModel.deleteReminder(selectedReminder) },
                    onDeny = { viewModel.hideConfirmationDialog() }
                )
            }
        }
    }
}

@Composable
fun RemindersList(
    reminders: List<Reminder>?,
    canEdit: Boolean,
    canDelete: Boolean,
    onItemCompleted: (Reminder) -> Unit,
    onEditClick: (Reminder) -> Unit,
    onDeleteClick: (Reminder) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(DSDimensions.Space4),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = DSDimensions.Space2)
            .verticalScroll(rememberScrollState())
    ) {
        reminders?.forEach { item ->
            RemindersItem(
                item,
                canEdit = canEdit,
                canDelete = canDelete,
                onItemCompleted = { onItemCompleted(item) },
                onEditClick = { onEditClick(item) },
                onDeleteClick = { onDeleteClick(item) }
            )
        }
    }
}

@Composable
fun RemindersItem(
    item: Reminder,
    canEdit: Boolean,
    canDelete: Boolean,
    onItemCompleted: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(DSDimensions.CornerRadius1))
            .border(
                width = DSDimensions.BorderRadius1,
                color = DSColours.FeatureColours.Reminders.Primary,
                shape = RoundedCornerShape(DSDimensions.CornerRadius1)
            )
            .background(DSColours.FeatureColours.Reminders.Container)
            .padding(DSDimensions.Space2)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = TimeFormatterUtil.formatLocalTime(item.time),
                fontSize = DSTypography.Body.Large,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.width(DSDimensions.Space4))

            Column(
                verticalArrangement = Arrangement.spacedBy(DSDimensions.Space2),
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = item.message,
                    fontSize = DSTypography.Body.Large,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "from ${item.from}",
                    fontSize = DSTypography.Body.Small,
                    fontWeight = FontWeight.Normal
                )
            }

            Spacer(modifier = Modifier.width(DSDimensions.Space2))

            CheckBox(
                itemChecked = item.completed,
                onItemChecked = onItemCompleted
            )
        }

        if (canEdit) {
            Spacer(modifier = Modifier.height(DSDimensions.Space2))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                ActionButton(
                    style = ActionButtonStyles.Edit,
                    onClick = onEditClick
                )

                if (canDelete) {
                    Spacer(modifier = Modifier.width(DSDimensions.Space4))
                    ActionButton(
                        style = ActionButtonStyles.Delete,
                        onClick = onDeleteClick
                    )
                }
            }
        }
    }
}