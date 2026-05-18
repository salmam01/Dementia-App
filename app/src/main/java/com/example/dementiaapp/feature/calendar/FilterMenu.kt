package com.example.dementiaapp.feature.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.design.DSTypography
import com.example.dementiaapp.feature.AllFeatureUI
import com.example.dementiaapp.feature.components.CheckBox
import com.example.dementiaapp.feature.components.buttons.ActionButton
import com.example.dementiaapp.feature.components.buttons.ActionButtonStyles

@Composable
fun FilterMenu(
    allFilterTypes: List<CalendarFeatureTypes>,
    appliedFilters: List<CalendarFeatureTypes>,
    onApplyFilters: (List<CalendarFeatureTypes>) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    var toggledFilters by rememberSaveable {
        mutableStateOf(value = appliedFilters)
    }

    Surface(
        color = DSColours.OnSurface.copy(alpha = 0.8f),
        modifier = modifier
            .fillMaxSize()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = onCancel)
                .padding(DSDimensions.Space4)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(DSDimensions.CornerRadius1))
                    .border(
                        width = DSDimensions.BorderRadius4,
                        color = DSColours.FeatureColours.Calendar.Primary,
                        shape = RoundedCornerShape(DSDimensions.CornerRadius1)
                    )
                    .background(DSColours.Surface)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DSColours.SurfaceVariant)
                        .padding(DSDimensions.Space4)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Tune,
                        contentDescription = "Filter",
                        tint = DSColours.FeatureColours.Calendar.Primary,
                        modifier = Modifier
                            .size(DSDimensions.Icon6)
                    )

                    Spacer(modifier = Modifier.width(DSDimensions.Space3))

                    Text(
                        text = "Filters",
                        fontSize = DSTypography.Headline.Large,
                        fontWeight = FontWeight.Bold
                    )
                }

                HorizontalDivider(
                    thickness = DSDimensions.DividerThickness1,
                    color = DSColours.Divider
                )

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    allFilterTypes.forEach { filter ->
                        when (filter) {
                            CalendarFeatureTypes.DIARY -> {
                                FilterItem(
                                    name = AllFeatureUI.DiaryUI.name,
                                    icon = AllFeatureUI.DiaryUI.icon,
                                    colour = AllFeatureUI.DiaryUI.colour,
                                    itemChecked = CalendarFeatureTypes.DIARY in toggledFilters,
                                    onItemChecked = {
                                        toggledFilters = toggleFilter(
                                            filter,
                                            appliedFilters = toggledFilters
                                        )
                                    }
                                )
                            }
                            CalendarFeatureTypes.MEDICATION -> {
                                FilterItem(
                                    name = AllFeatureUI.MedicationUI.name,
                                    icon = AllFeatureUI.MedicationUI.icon,
                                    colour = AllFeatureUI.MedicationUI.colour,
                                    itemChecked = CalendarFeatureTypes.MEDICATION in toggledFilters,
                                    onItemChecked = {
                                        toggledFilters = toggleFilter(
                                            filter,
                                            appliedFilters = toggledFilters
                                        )
                                    }
                                )
                            }
                            CalendarFeatureTypes.REMINDERS -> {
                                FilterItem(
                                    name = AllFeatureUI.RemindersUI.name,
                                    icon = AllFeatureUI.RemindersUI.icon,
                                    colour = AllFeatureUI.RemindersUI.colour,
                                    itemChecked = CalendarFeatureTypes.REMINDERS in toggledFilters,
                                    onItemChecked = {
                                        toggledFilters = toggleFilter(
                                            filter,
                                            appliedFilters = toggledFilters
                                        )
                                    }
                                )
                            }
                        }

                        HorizontalDivider(
                            thickness = DSDimensions.DividerThickness1,
                            color = DSColours.Divider
                        )
                    }
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = DSDimensions.Space4)
                ) {
                    ActionButton(
                        style = ActionButtonStyles.ApplyFilter,
                        onClick = { onApplyFilters(toggledFilters) },

                        shape = RoundedCornerShape(DSDimensions.CornerRadius3),
                        fontSize = DSTypography.Body.Large,
                        iconSize = DSDimensions.Icon3,

                        verticalPadding = DSDimensions.Space4,
                        horizontalPadding = DSDimensions.Space4,
                    )
                }
            }
        }
    }
}

@Composable
fun FilterItem(
    name: String,
    icon: ImageVector,
    colour: Color,
    itemChecked: Boolean,
    onItemChecked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(DSDimensions.Space3),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(DSDimensions.Space4)
    ) {
        CheckBox(
            itemChecked = itemChecked,
            onItemChecked = onItemChecked,
            shape = RoundedCornerShape(DSDimensions.CornerRadius1),
            backgroundColourChecked = DSColours.CheckBoxBackground,
            borderColour = DSColours.CheckBoxOutline
        )

        Icon(
            imageVector = icon,
            contentDescription = name,
            tint = colour,
            modifier = Modifier.size(DSDimensions.Icon6)
        )

        Text(
            text = name,
            fontSize = DSTypography.Body.Large,
            fontWeight = FontWeight.Medium
        )
    }
}

fun toggleFilter(
    filter: CalendarFeatureTypes,
    appliedFilters: List<CalendarFeatureTypes>
): List<CalendarFeatureTypes> {
    return if (filter in appliedFilters) {
        appliedFilters - filter
    } else {
        appliedFilters + filter
    }
}