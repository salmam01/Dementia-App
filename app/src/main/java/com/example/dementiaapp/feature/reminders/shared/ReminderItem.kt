package com.example.dementiaapp.feature.reminders.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.design.DSTypography
import com.example.dementiaapp.domain.models.Reminder
import com.example.dementiaapp.domain.models.UserRole
import com.example.dementiaapp.feature.components.CheckBox
import com.example.dementiaapp.feature.components.buttons.ActionButton
import com.example.dementiaapp.feature.components.buttons.ActionButtonStyles
import com.example.dementiaapp.util.time.TimeFormatterUtil

@Composable
fun ReminderItem(
    modifier: Modifier = Modifier,
    item: Reminder,
    userRole: UserRole,

    canEdit: Boolean = true,
    canDelete: Boolean = true,

    onItemCompleted: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,

    containerColour: Color,
    titleFontSize: TextUnit,
    contentFontSize: TextUnit
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