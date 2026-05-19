package com.example.dementiaapp.feature.myfamily.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dementiaapp.design.DSColours
import com.example.dementiaapp.design.DSDimensions
import com.example.dementiaapp.domain.models.FeatureAction
import com.example.dementiaapp.domain.models.feature.Person
import com.example.dementiaapp.feature.components.ConfirmationDialogue
import com.example.dementiaapp.feature.components.DetailsRow
import com.example.dementiaapp.feature.components.ProfileCard
import com.example.dementiaapp.feature.myfamily.main.MyFamilyViewModel
import com.example.dementiaapp.localization.LocalizedStrings
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@Composable
fun MyFamilyDetailScreen(
    onReturn: () -> Unit,
    onEditEntry: (String?) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: MyFamilyViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val strings = LocalizedStrings.current

    val selectedEntry = state.selectedEntry
    val canAdd = viewModel.hasAccessToAction(FeatureAction.ADD)
    val canEdit = canAdd
            && viewModel.hasAccessToAction(FeatureAction.EDIT)
    val canDelete = canEdit
            && viewModel.hasAccessToAction(FeatureAction.DELETE)

    if (selectedEntry == null) {
        onReturn()
    } else {
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                EntryDetails(
                    person = selectedEntry,
                    canEdit = canEdit,
                    canDelete = canDelete,
                    onEditClick = { onEditEntry(state.selectedEntry?.id) },
                    onDeleteClick = { viewModel.showConfirmationDialog() }
                )
            }

            if (state.showConfirmationDialog) {
                val entryName = selectedEntry.fullName.substringAfter(" ")
                ConfirmationDialogue(
                    text = "${strings.deleteLowerCase} \"$entryName\"",
                    onConfirm = { viewModel.deleteEntry(selectedEntry) },
                    onDeny = { viewModel.hideConfirmationDialog() }
                )
            }
        }
    }
}

@Composable
fun EntryDetails(
    person: Person,
    canEdit: Boolean,
    canDelete: Boolean,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val strings = LocalizedStrings.current

    val age = person.birthday?.let {
        LocalDate.now().year - it.year
    }.toString()

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        ProfileCard(
            name = person.fullName,
            description = person.relationShip,
            image = person.image,

            canEdit = canEdit,
            canDelete = canDelete,

            onEditClick = onEditClick,
            onDeleteClick = onDeleteClick,

            containerColour = DSColours.FeatureColours.MyFamily.Container,
            imageOutlineColour = DSColours.FeatureColours.MyFamily.Outline,
            dividerColour = DSColours.FeatureColours.MyFamily.Outline
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            DetailsRow(
                label = strings.fullName,
                detail = person.fullName
            )
            DetailsRow(
                label = strings.relationship,
                detail = person.relationShip
            )
            DetailsRow(
                label = strings.nickname,
                detail = person.nickName
            )
            DetailsRow(
                label = strings.age,
                detail = age
            )
            DetailsRow(
                label = strings.address,
                detail = person.address
            )
            DetailsRow(
                label = strings.number,
                detail = person.number
            )
            DetailsRow(
                label = strings.hobbies,
                detail = person.hobbies
            )
            DetailsRow(
                label = strings.favouriteColour,
                detail = person.favouriteColour
            )
            DetailsRow(
                label = strings.favouriteBook,
                detail = person.favouriteBook
            )
            DetailsRow(
                label = strings.favouriteFood,
                detail = person.favouriteFood
            )
            DetailsRow(
                label = strings.favouriteMusic,
                detail = person.favouriteMusic
            )
        }
    }
}