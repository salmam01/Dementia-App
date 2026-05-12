package com.example.dementiaapp.feature.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.design.DSTypography
import com.example.dementiaapp.domain.time.TimeProvider.toDayTime
import com.example.dementiaapp.feature.components.DatePickerSection
import com.example.dementiaapp.feature.components.DayTimeSection
import com.example.dementiaapp.feature.components.buttons.ActionButton
import com.example.dementiaapp.feature.components.buttons.ActionButtonStyles
import com.example.dementiaapp.feature.components.buttons.StickyActionButton
import com.example.dementiaapp.util.time.DayTime
import com.example.dementiaapp.util.time.TimeFormatterUtil
import org.koin.androidx.compose.koinViewModel

@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: CalendarViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            DatePickerSection(
                date = state.selectedDate,
                onPreviousClick = { viewModel.previousDay() },
                onNextClick = { viewModel.nextDay() },
            )

            Spacer(modifier = Modifier.height(DSDimensions.Space4))

            ActionButton(
                style = ActionButtonStyles.Filter,
                onClick = { viewModel.toggleFilterMenu() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = DSDimensions.Space2),

                verticalPadding = DSDimensions.Space2,
                fontSize = DSTypography.Body.Large,
                iconSize = DSDimensions.Icon4,
                contentSpacing = DSDimensions.Space2
            )

            Spacer(modifier = Modifier.height(DSDimensions.Space4))

            CalendarEntries(
                state.filteredCalendarEntries,
                state.appliedFilters,
                onEntryClick = { }
            )
        }
        StickyActionButton(
            text = "Month View",
            icon = Icons.Filled.CalendarMonth,
            colour = DSColours.FeatureColours.Calendar.Primary,
            onAddClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
        if (state.showFilterMenu) {
            FilterMenu(
                allFilterTypes = state.calendarFeatureTypes,
                appliedFilters = state.appliedFilters,
                onApplyFilters = { filters ->
                    viewModel.toggleFilterOptions(filters)
                    viewModel.toggleFilterMenu()
                 },
                onCancel = { viewModel.toggleFilterMenu() }
            )
        }
    }
}

@Composable
fun CalendarEntries(
    entries: List<CalendarEntry>,
    appliedFilters: List<CalendarFeatureTypes>,
    onEntryClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(DSDimensions.Space4),
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(
                bottom = DSDimensions.AddButtonSpace
            )
    ) {
        val grouped = entries
            .groupBy { it.time.toDayTime() }

        DayTime.entries.forEach { dayTime ->
            val items = grouped[dayTime].orEmpty()

            if (items.isNotEmpty()) {
                DayTimeSection(dayTime = dayTime)

                items.forEach { entry ->
                    CalendarEntry(
                        entry = entry,
                        onClick = { onEntryClick(entry.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun CalendarEntry(
    entry: CalendarEntry,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(DSDimensions.Space3),
        modifier = modifier
            .fillMaxWidth()
            .background(DSColours.SurfaceVariant)
            .border(
                width = DSDimensions.BorderRadius2,
                color = entry.colour,
                shape = RoundedCornerShape(0)
            )
    ) {
        Column(
            modifier = Modifier
                .background(entry.colour)
                .fillMaxHeight()
        ) {
            Icon(
                imageVector = entry.icon,
                contentDescription = null,
                tint = DSColours.OnPrimary,
                modifier = Modifier
                    .padding(DSDimensions.Space4)
                    .size(DSDimensions.Icon8)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(vertical = DSDimensions.Space3)
        ) {
            Text(
                text = entry.title,
                fontSize = DSTypography.Element.Large,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(DSDimensions.Space2))

            Text(
                text = TimeFormatterUtil.formatLocalTime(entry.time),
                fontSize = DSTypography.Body.Medium,
            )
        }

        IconButton(
            modifier = Modifier
                .padding(end = DSDimensions.Space1),
            onClick = { }
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBackIosNew,
                contentDescription = null,
                tint = DSColours.OnSurface,
                modifier = Modifier
                    .size(DSDimensions.Icon5)
                    .scale(scaleX = -1f, scaleY = 1f)
            )
        }
    }

}