package com.example.dementiaapp.feature.diary.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.domain.models.DiaryEntry
import com.example.dementiaapp.feature.components.TitleSection
import com.example.dementiaapp.feature.components.buttons.FormButtons
import com.example.dementiaapp.feature.components.form.DatePickerForm
import com.example.dementiaapp.feature.components.form.TextFieldForm
import com.example.dementiaapp.feature.components.form.TimePickerForm
import com.example.dementiaapp.localization.LocalizedStrings
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun DiaryFormScreen(
    diaryEntryId: String?,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: DiaryFormViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val strings = LocalizedStrings.current

    LaunchedEffect(diaryEntryId) {
        viewModel.clearState()
        if (diaryEntryId != null) {
            viewModel.getEntryById(id = diaryEntryId)
        }
    }

    val isEditing = state.isEditing
    val title =
        if (isEditing) "${strings.edit} ${strings.diary}"
        else "${strings.add} ${strings.diary}"

    val icon =
        if (isEditing) Icons.Rounded.Edit
        else Icons.Rounded.Add


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DSColours.Surface)
    ) {
        TitleSection(
            title = title,
            icon = icon,
            colour = DSColours.FeatureColours.Diary.Primary
        )

        DiaryFormContent(
            draftEntry = state.draftEntry,
            onTitleChange = { viewModel.setTitle(it) },
            onContentChange = { viewModel.setContent(it) },
            onTimeSelected = { viewModel.setTime(it) },
            onDateSelected = { viewModel.setDate(it) },
            modifier = Modifier.weight(1f)
        )

        FormButtons(
            colour = DSColours.FeatureColours.Diary.Primary,
            isEditing = isEditing,
            isEnabled = state.isValid,
            onAddOrSaveClick = {
                viewModel.addOrEditEntry()
                viewModel.clearState()
                onBack()
            },
            onCancelClick = {
                viewModel.clearState()
                onBack()
            }
        )
    }
}

@Composable
fun DiaryFormContent(
    draftEntry: DiaryEntry,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit,
    onDateSelected: (LocalDate) -> Unit,
    onTimeSelected: (LocalTime) -> Unit,
    modifier: Modifier = Modifier
) {
    val strings = LocalizedStrings.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        TextFieldForm(
            label = strings.title,
            initialValue = draftEntry.title,
            onValueChange = onTitleChange,
            colour = DSColours.FeatureColours.Diary.Primary,
            backgroundColour = DSColours.FeatureColours.Diary.Accent
        )

        TextFieldForm(
            label = strings.content,
            initialValue = draftEntry.content,
            onValueChange = onContentChange,
            colour = DSColours.FeatureColours.Diary.Primary
        )

        DatePickerForm(
            date = draftEntry.date,
            onDateSelected = onDateSelected,
            colour = DSColours.FeatureColours.Diary.Primary
        )

        TimePickerForm(
            time = draftEntry.time,
            onTimeSelected = onTimeSelected,
            colour = DSColours.FeatureColours.Diary.Primary
        )
    }
}