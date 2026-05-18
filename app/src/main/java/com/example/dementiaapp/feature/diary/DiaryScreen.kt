package com.example.dementiaapp.feature.diary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.design.DSTypography
import com.example.dementiaapp.domain.models.feature.DiaryEntry
import com.example.dementiaapp.feature.components.ConfirmationDialogue
import com.example.dementiaapp.feature.components.time.DatePickerSection
import com.example.dementiaapp.feature.components.buttons.ActionButton
import com.example.dementiaapp.feature.components.buttons.ActionButtonStyles
import com.example.dementiaapp.feature.components.buttons.StickyActionButton
import org.koin.androidx.compose.koinViewModel

@Composable
fun DiaryScreen(
    onAddOrEditEntry: (String?) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: DiaryViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val entry = state.diaryEntry

    LaunchedEffect(Unit) {
        viewModel.refresh()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        DatePickerSection(
            date = state.selectedDate,
            onNextClick = { viewModel.nextDay() },
            onPreviousClick = { viewModel.previousDay() }
        )

        Box(
           modifier = Modifier
               .weight(1f)
               .fillMaxWidth()
        ) {
            if (entry == null) {
                EmptyDiaryEntry()
                StickyActionButton(
                    text = "Add Entry",
                    colour = DSColours.FeatureColours.Diary.Primary,
                    onClick = { onAddOrEditEntry(null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                )
            } else {
                DiaryEntry(
                    entry = entry,
                    onEditClick = { onAddOrEditEntry(it) },
                    onDeleteClick = {
                        viewModel.showConfirmationDialog()
                    }
                )
                if (state.showConfirmationDialog) {
                    ConfirmationDialogue(
                        text = "delete the entry \"${entry.title}\"",
                        onConfirm = { viewModel.deleteDiaryEntry(entry) },
                        onDeny = { viewModel.hideConfirmationDialog() }
                    )
                }
            }
        }
    }
}

@Composable
fun DiaryEntry(
    entry: DiaryEntry,
    onEditClick: (String) -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val previewImages = entry.images?.take(3)

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(DSColours.FeatureColours.Diary.Accent)
                .padding(DSDimensions.Space4)
        ) {
            Text(
                text = entry.title,
                fontSize = DSTypography.Headline.Large,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            modifier = Modifier
                .padding(DSDimensions.Space4)
        ) {
            Text(
                text = entry.content,
                fontSize = DSTypography.Body.Large,
                fontWeight = FontWeight.Normal
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(DSDimensions.Space4)
        ) {
            Text(
                text = entry.time.toString(),
                fontSize = DSTypography.Body.Small,
                fontWeight = FontWeight.Normal
            )

            // TODO: Check patient permissions
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                ActionButton(
                    style = ActionButtonStyles.Edit,
                    onClick = { onEditClick(entry.id) }
                )
                Spacer(modifier = Modifier.width(DSDimensions.Space4))
                ActionButton(
                    style = ActionButtonStyles.Delete,
                    onClick = onDeleteClick
                )
            }
        }

        HorizontalDivider(
            modifier = Modifier
                .padding(horizontal = DSDimensions.Space5),
            thickness = DSDimensions.DividerThickness2,
            color = DSColours.Divider
        )
    }

}

@Composable
fun ImagePreview(
    modifier: Modifier = Modifier
) {

}

@Composable
fun EmptyDiaryEntry(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(
                bottom = DSDimensions.AddButtonSpace
            )
    ) {
        Text(
            text = "No Diary Entry for this date yet.\n" +
                    "Tap the button below to add one!",
            fontSize = DSTypography.Body.Large,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}